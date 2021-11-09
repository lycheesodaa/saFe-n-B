import React, { useState, useEffect } from 'react';
import { Form, Button, Alert } from "react-bootstrap";
//login
import { connect } from "react-redux";
import { loginEmployee, loginFirm } from "../../../actions/authActions";
import { useNavigate } from 'react-router-dom';
import ToggleButton from "react-bootstrap/ToggleButton";
import ToggleButtonGroup from "react-bootstrap/ToggleButtonGroup";
import {
  Link,
  Stack,
  Checkbox,
  TextField,
  IconButton,
  InputAdornment,
  FormControlLabel
} from '@mui/material';
// ----------------------------------------------------------------------

const LoginForm = ({ auth, loginEmployee, loginFirm, error }) => {
  let navigate = useNavigate();
  const [value, setValue] = React.useState(1);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  function validateForm() {
    return email.length > 0 && password.length > 0;
  }

  function resetLoginForm() {
    setEmail("");
    setPassword("");
  }

  function handleSubmit(event) {
    event.preventDefault();
    if (value == 1) {
      loginFirm(email, password);
    } else {
      loginEmployee(email, password);
    }
  }

  function quickLoginFirm() {
    setEmail("harshitj.2019@sis.smu.edu.sg")
    setPassword("test123")
    loginFirm(email, password);
  }

  function quickLoginEmployee() {
    setEmail("employee1@email.com")
    setPassword("test123")
    loginEmployee(email, password);
  }

  useEffect(() => {
    console.log(auth);
    if (auth.isAuthenticated === true) {
      navigate("/user/stats");
    }
  }, [auth]);

  useEffect(() => {
    if (error.status != null) {
      resetLoginForm();
      setErrorMsg("Invalid email and password");
    }
  }, [error]);

  return (
    <Form onSubmit={handleSubmit}>
      {/* <Stack spacing={3}>
          <TextField
            fullWidth
            autoComplete="username"
            type="email"
            label="Email address"
          />

          <TextField
            fullWidth
            autoComplete="current-password"
            type="password"
            label="Password"
            // InputProps={{
            //   endAdornment: (
            //     <InputAdornment position="end">
            //       <IconButton onClick={handleShowPassword} edge="end">
            //         <Icon icon={showPassword ? eyeFill : eyeOffFill} />
            //       </IconButton>
            //     </InputAdornment>
            //   )
            // }}
          />
        </Stack> */}
      {errorMsg && <Alert variant="danger">{errorMsg}</Alert>}
      <center>
        <ToggleButtonGroup
          name="value"
          type="radio"
          value={value}
          onChange={(val) => {
            setValue(val)
          }}
        >
          <ToggleButton value={1} variant="outline-primary">Firm</ToggleButton>
          <ToggleButton value={2} variant="outline-primary">Employee</ToggleButton>
        </ToggleButtonGroup>
      </center>
      <Form.Group size="lg" controlId="email">
        <Form.Label>Email</Form.Label>
        <Form.Control
          autoFocus
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </Form.Group>
      <Form.Group size="lg" controlId="password">
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </Form.Group>
      <Button block size="lg" variant="success" type="submit" disabled={!validateForm()}>
        Login
      </Button>
      <Button block size="lg" variant="success" type="submit" onClick={quickLoginFirm}>
        Quick Login: firm
      </Button>
      <Button block size="lg" variant="success" type="submit" onClick={quickLoginEmployee}>
        Quick Login: employee
      </Button>
    </Form>
  );
}
const mapStateToProps = (state) => ({
  auth: state.auth,
  error: state.error
});

const mapDispatchToProps = dispatch => {
  return {
    loginFirm: (email, password) => dispatch(loginFirm(email, password)),
    loginEmployee: (email, password) => dispatch(loginEmployee(email, password))
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);

