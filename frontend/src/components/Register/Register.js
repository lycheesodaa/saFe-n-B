import React, { useState } from 'react';
import { Col, Row, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import "./Register.css";
import Header from "../Header/HeaderLogout";

export default function Register() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [address, setAddress] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    function validateForm() {
        return email.length > 0 && password.length > 0;
    }

    function handleSubmit(event) {
        event.preventDefault();
    }

    return (
        <>
        <Header/>
        <div className="Register">
        <h1 className="d-flex justify-content-center mt-4">Welcome to the future</h1>
            <Form onSubmit={handleSubmit}>
                <FormGroup>
                    <Label for="username">Username</Label>
                    <Input type="text" name="username" id="username" placeholder="Enter your username" />
                </FormGroup>

                <FormGroup className="mt-4">
                    <Label for="email">Email</Label>
                    <Input type="email" name="email" id="email" placeholder="Enter your email" />
                </FormGroup>

                <FormGroup className="mt-4">
                    <Label for="phone">Phone Number</Label>
                    <Input type="number" name="phone" id="phone" placeholder="Enter your phone number" />
                </FormGroup>

                <FormGroup className="mt-4">
                    <Label for="address">Address</Label>
                    <Input type="text" name="address" id="address" placeholder="Enter your address" />
                </FormGroup>


                <FormGroup className="mt-4">
                    <Label for="option">Who do you want to be?</Label>
                    <Input type="select" name="select" id="exampleSelect">
                        <option>Borrower</option>
                        <option>Lender</option>
                    </Input>
                </FormGroup>

                <FormGroup className="mt-4">
                    <Label for="option">Payment Method</Label>
                    <Input type="select" name="select" id="exampleSelect">
                        <option>Apple Pay</option>
                        <option>Google Pay</option>
                        <option>Samsung Pay</option>
                        <option>Samsung Pay</option>
                        <option>Credit Card</option>
                        <option>Local Mobile Wallet</option>
                    </Input>
                </FormGroup>

                <FormGroup className="mt-4">
                    <Label for="password">Password</Label>
                    <Input type="password" name="password" id="password" placeholder="Enter your password" />
                </FormGroup>

                <FormGroup className="mt-4">
                    <Label for="cfmPassword">Confirm Password</Label>
                    <Input type="password" name="cfmPassword" id="cfmPassword" placeholder="Enter your password again" />
                </FormGroup>

                <FormGroup className="mt-4">
                <Button block size="lg" type="submit" disabled={!validateForm()}>
                        Register
                    </Button>
                </FormGroup>

            </Form>
        </div>
        </>
    );
}