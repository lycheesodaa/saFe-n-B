<<<<<<< HEAD
import React, { useState, useEffect } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import { createFirmAccount, createEmployeeAccount } from "../../../actions/authActions";
import { connect } from "react-redux";
import { useNavigate } from 'react-router-dom';
import ToggleButton from "react-bootstrap/ToggleButton";
import ToggleButtonGroup from "react-bootstrap/ToggleButtonGroup";
import { values } from "lodash";

function RegisterForm({ auth, createFirmAccount, createEmployeeAccount, error }) {
  const navigate = useNavigate();
  const [value, setValue] = React.useState(1);
  const [registrationDate, setRegistrationDate] = useState("");
  const [firmEmail, setFirmEmail] = useState("");
  const [firmName, setFirmName] = useState("");
  const [typeOfOutlet, setTypeOfOutlet] = useState("");
  const [firmContact, setFirmContact] = useState("");

  const [employeeEmail, setEmployeeEmail] = useState("");
  const [employeeName, setEmployeeName] = useState("");
  const [firmEmailEmployee, setFirmEmailEmployee] = useState("");
  const [nric, setNric] = useState("");
  const [employeeContact, setEmployeeContact] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [employeeAddress, setEmployeeAddress] = useState("");

  const [password, setPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");



  function validateForm() {
    return (firmEmail.length > 0 && password.length > 0 && password == newPassword) || (employeeEmail.length > 0 && password.length > 0 && password == newPassword);
  }

  function handleSubmit(event) {
    event.preventDefault();
    if (value == 1) {
      //firm
      const [year, month, day] = registrationDate.split('-')
      var registrationDateToSend = `${day}/${month}/${year}`
      createFirmAccount(firmName, firmEmail, registrationDateToSend, typeOfOutlet, firmContact, password);
    } else if (value == 2) {
      //employee
      const [year, month, day] = dateOfBirth.split('-')
      var dateOfBirthToSend = `${day}/${month}/${year}`
      createEmployeeAccount(employeeName, firmEmailEmployee, employeeEmail, nric, dateOfBirthToSend, employeeAddress, employeeContact, password);
    }

  }

  function resetForm() {
    setRegistrationDate("");
    setFirmEmail("");
    setFirmName("");
    setTypeOfOutlet("");
    setFirmContact("");

    setEmployeeEmail("");
    setEmployeeName("");
    setFirmEmailEmployee("");
    setNric("");
    setEmployeeContact("");
    setDateOfBirth("");
    setEmployeeAddress("");

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

  const firmForm = (
    <>
      <Form.Group size="lg" controlId="firmName" >
        <Form.Label>Name</Form.Label>
        <Form.Control
          autoFocus
          type="text"
          value={firmName}
          onChange={(e) => setFirmName(e.target.value)}
        />
      </Form.Group>
      <Form.Group size="lg" controlId="firmEmail">
        <Form.Label>Email</Form.Label>
        <Form.Control
          autoFocus
          type="email"
          value={firmEmail}
          onChange={(e) => setFirmEmail(e.target.value)}
        />
      </Form.Group>
      <Form.Group size="lg" controlId="registrationDate">
        <Form.Label>Registration Date</Form.Label>
        <Form.Control
          autoFocus
          type="date"
          value={registrationDate}
          onChange={(e) => {
            setRegistrationDate(e.target.value)
          }}
        />
      </Form.Group>
      <Form.Group>
        <Form.Label>Type of outlet</Form.Label>
        <Form.Control
          as="select"
          custom
          onChange={(e) => {
            setTypeOfOutlet(e.target.value);
          }}
        >
          <option value="Restaurant">Restaurant</option>
          <option value="Bar">Bar</option>
          <option value="Pub">Pub</option>
          <option value="Cafeteria">Cafeteria</option>
          <option value="Coffee Shop">Coffee Shop</option>
          <option value="Discotheque">Discotheque</option>
          <option value="Food Court">Food Court</option>
        </Form.Control>
      </Form.Group>
      <Form.Group size="lg" controlId="firmContact">
        <Form.Label>Contact Number</Form.Label>
        <Form.Control
          autoFocus
          type="tel"
          value={firmContact}
          onChange={(e) => setFirmContact(e.target.value)}
        />
      </Form.Group>
    </>)

  const employeeForm = (
    <>
      <Form.Group size="lg" controlId="employeeName" >
        <Form.Label>Name</Form.Label>
        <Form.Control
          autoFocus
          type="text"
          value={employeeName}
          onChange={(e) => setEmployeeName(e.target.value)}
        />
      </Form.Group>
      <Form.Group size="lg" controlId="firmEmail" >
        <Form.Label>Firm Email</Form.Label>
        <Form.Control
          autoFocus
          type="text"
          value={firmEmailEmployee}
          onChange={(e) => setFirmEmailEmployee(e.target.value)}
        />
      </Form.Group>
      <Form.Group size="lg" controlId="employeeEmail">
        <Form.Label>Email</Form.Label>
        <Form.Control
          autoFocus
          type="email"
          value={employeeEmail}
          onChange={(e) => setEmployeeEmail(e.target.value)}
        />
      </Form.Group>
      <Form.Group size="lg" controlId="nric">
        <Form.Label>NRIC/FIN</Form.Label>
        <Form.Control
          autoFocus
          type="text"
          value={nric}
          onChange={(e) => setNric(e.target.value)}
        />
      </Form.Group>
      <Form.Group size="lg" controlId="employeeDateOfBirth">
        <Form.Label>Date of Birth</Form.Label>
        <Form.Control
          autoFocus
          type="date"
          value={dateOfBirth}
          onChange={(e) => {
            setDateOfBirth(e.target.value)
          }}
        />
      </Form.Group>
      <Form.Group size="lg" controlId="employeeAddress">
        <Form.Label>Address</Form.Label>
        <Form.Control
          autoFocus
          type="address"
          value={employeeAddress}
          onChange={(e) => setEmployeeAddress(e.target.value)}
        />
      </Form.Group>
      <Form.Group size="lg" controlId="employeeContact">
        <Form.Label>Contact</Form.Label>
        <Form.Control
          autoFocus
          type="tel"
          value={employeeContact}
          onChange={(e) => setEmployeeContact(e.target.value)}
        />
      </Form.Group>

    </>
  )

  return (
    <div className="Registration">
      <Form onSubmit={handleSubmit}>
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
        {value == 1 ? (firmForm) : (employeeForm)}
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
    createFirmAccount: (firmName, firmEmail, registrationDate, typeOfOutlet, firmContact, password) => dispatch(createFirmAccount(firmName, firmEmail, registrationDate, typeOfOutlet, firmContact, password)),
    createEmployeeAccount: (employeeName, firmEmailEmployee, employeeEmail, nric, dateOfBirth, employeeAddress, employeeContact, password) => dispatch(createEmployeeAccount(employeeName, firmEmailEmployee, employeeEmail, nric, dateOfBirth, employeeAddress, employeeContact, password))
  };
};

=======
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

>>>>>>> 5edcc10ffe0cb908ab82af6dae873676e6872857
export default connect(mapStateToProps, mapDispatchToProps)(RegisterForm);