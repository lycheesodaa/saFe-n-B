import React, { useState } from 'react';
import { Col, Row, Button, Form, FormGroup, Label, Input, CustomInput } from 'reactstrap';
import "./StartTradeBorrower.css";
import Header from "../Header/HeaderLoginBorrower";

import { useHistory } from 'react-router-dom';

export default function StartTradeBorrower() {
    const history = useHistory();
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [address, setAddress] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [interestRate, setInterestRate] = useState("4")
    function validateForm() {
        return true;
    }

    function handleSubmit(event) {
        event.preventDefault();
        history.push('./my-trades-borrower')
    }

    return (
        <>
        <Header />
        <div className="StartTrade">
            <h1 className="d-flex justify-content-center mt-4">Start a trade</h1>
            <Form onSubmit={handleSubmit}>
                <FormGroup className="mt-4">
                    <Label for="amtRequired">Amount Required</Label>
                    <Input type="number" name="amtRequired" id="amtRequired" placeholder="" />
                </FormGroup>
                <p>*Based on your credit rating, you can borrow a maximum of $100,000</p>
                <FormGroup className="mt-4">
                    <Label for="purpose">Purpose of funds</Label>
                    <Input type="text" name="purpose" id="purpose" placeholder="" />
                </FormGroup>

                <FormGroup className="mt-4">
                    <Label for="payback">Desired Payback Date</Label>
                    <Input type="date" name="payback" id="payback" placeholder="" />
                </FormGroup>

                <FormGroup className="mt-4">
                    <Label for="interestRate">Desired Interest Rate</Label>
                    <CustomInput type="range" id="interestRate" name="interestRate" min="2" max="8" value={interestRate} onChange={(e) => setInterestRate(e.target.value)} />
                    <span>{interestRate}</span>
                    <p>*On your payback date, you will have to pay $52,000</p>
                </FormGroup>


                <FormGroup className="mt-4">
                    <Button block size="lg" type="submit" disabled={!validateForm()}>
                        Confirm
                    </Button>
                </FormGroup>

            </Form>
        </div>
        </>
    );
}