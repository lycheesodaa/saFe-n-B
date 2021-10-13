import React, { useState, useEffect } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import { createAccount } from "../../../actions/authActions";
import { connect } from "react-redux";
import { useNavigate } from 'react-router-dom';

function RegisterForm({ auth, createAccount, error }) {
  const navigate = useNavigate();
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  function validateForm() {
    return email.length > 0 && password.length > 0 && password == newPassword;
  }

  function handleSubmit(event) {
    event.preventDefault();
    createAccount(email, password, dateOfBirth)
  }

  function resetForm() {
    setEmail("");
    setDateOfBirth("");
    setPassword("");
    setNewPassword("");
  }

  useEffect(() => {
    if (auth.isAuthenticated === true) {
      alert("successfully registered");
      navigate("/user/stats");
    }
  }, [auth]);

  useEffect(() => {
    if (error.status != null) {
      resetForm();
      setErrorMsg("User already exists.");
    }
  }, [error]);

  return (
    <div className="Registration">
      <Form onSubmit={handleSubmit}>
        <Form.Group size="lg" controlId="email">
          {errorMsg && <Alert variant="danger">{errorMsg}</Alert>}
          <Form.Label>Email</Form.Label>
          <Form.Control
            autoFocus
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="dateOfBirth">
          <Form.Label>Firm's Registration Date</Form.Label>
          <Form.Control
            autoFocus
            type="date"
            value={dateOfBirth}
            onChange={(e) => setDateOfBirth(e.target.value)}
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
        <Form.Group size="lg" controlId="newPassword">
          <Form.Label>Confirm Password</Form.Label>
          <Form.Control
            type="password"
            value={newPassword}
            onChange={(e) => setNewPassword(e.target.value)}
          />
        </Form.Group>
        <Button block size="lg" type="submit" disabled={!validateForm()}>
          Create New Account
        </Button>
      </Form>
    </div>
  );
}

const mapStateToProps = (state) => ({
  auth: state.auth,
  error: state.error
});

const mapDispatchToProps = dispatch => {
  return {
    createAccount: (email, password, dateOfBirth) => dispatch(createAccount(email, password, dateOfBirth))
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(RegisterForm);