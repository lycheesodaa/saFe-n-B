import React, { useState, useEffect } from 'react';
import { Form, Button, Alert } from "react-bootstrap";
//login
import { connect } from "react-redux";
import { login } from "../../../actions/authActions";
import { useNavigate } from 'react-router-dom';
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

const LoginForm = ({ auth, login, error }) => {
  let navigate = useNavigate();
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
    login(email, password);
  }

  useEffect(() => {
    console.log(auth);
    if (auth.isAuthenticated === true) {
      alert("successfully logged in");
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
      </Form>
  );
}
const mapStateToProps = (state) => ({
  auth: state.auth,
  error: state.error
});

const mapDispatchToProps = dispatch => {
  return {
    login: (email, password) => dispatch(login(email, password))
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);
