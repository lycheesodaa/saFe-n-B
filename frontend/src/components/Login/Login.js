import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import "./Login.css";
import { useHistory } from 'react-router-dom';
import Header from "../Header/HeaderLogout";


export default function Login() {
    const history = useHistory();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    function validateForm() {
        return email.length > 0 && password.length > 0;
    }

    function handleSubmit(event) {
        event.preventDefault();
        if (email == "borrower@email.com") {
            history.push("/start-trade-borrower");
        } else {
            history.push("/start-trade-lender")
        }
    }

    return (
        <>
        <Header />
        <div className="Login">
        <h1 className="d-flex justify-content-center mt-4">Welcome to the future</h1>
            <Form onSubmit={handleSubmit}>
                <Form.Group size="lg" controlId="email">
                    <Form.Label className="d-flex justify-content-center mt-4">Email</Form.Label>
                    <Form.Control
                        autoFocus
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </Form.Group>

                <Form.Group size="lg" controlId="password">
                    <Form.Label className="d-flex justify-content-center mt-4">Password</Form.Label>
                    <Form.Control
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Form.Group>

                <div className="d-flex justify-content-center mt-4">
                    <Button block size="lg" type="submit" disabled={!validateForm()}>
                        Login
                    </Button>
                </div>
            </Form>
        </div>
        </>
    );
}
