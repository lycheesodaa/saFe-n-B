import React from "react";
import "./StartTradeLender.css";
import { Card, CardGroup, ListGroup } from "react-bootstrap";
import Header from "../Header/HeaderLoginLender";

export default function StartTradeLender() {
    return (
        <>
        <Header />
        <div className='start-trade-lender '>
            <h1 className="mt-4">Start a trade</h1>
            <CardGroup style={{ marginBottom: 20 + 'px' }}>
                <Card>
                    <ListGroup horizontal>
                        <ListGroup.Item>Name: Jack Ponting</ListGroup.Item>
                        <ListGroup.Item>Credit Score: 830 </ListGroup.Item>
                        <ListGroup.Item>Funds required: $50,000</ListGroup.Item>
                        <ListGroup.Item>Purpose: Sister's marriage</ListGroup.Item>
                        <ListGroup.Item>Desired Payback Date: 29/04/2021</ListGroup.Item>
                        <ListGroup.Item>Desired Interest Rate: 4%</ListGroup.Item>
                        <ListGroup.Item variant="success" className="d-flex align-items-center imp">Accept</ListGroup.Item>
                        <ListGroup.Item variant="danger" className="d-flex align-items-center imp">Decline</ListGroup.Item>
                    </ListGroup>
                </Card>
            </CardGroup>

            <CardGroup style={{ marginBottom: 20 + 'px' }}>
                <Card>
                    <ListGroup horizontal>
                        <ListGroup.Item>Name: Abbie Pan</ListGroup.Item>
                        <ListGroup.Item>Credit Score: 830</ListGroup.Item>
                        <ListGroup.Item>Funds required: $75,000</ListGroup.Item>
                        <ListGroup.Item>Purpose: x</ListGroup.Item>
                        <ListGroup.Item>Desired Payback Date: x</ListGroup.Item>
                        <ListGroup.Item>Desired Interest Rate: x</ListGroup.Item>
                        <ListGroup.Item variant="success" className="d-flex align-items-center imp">Accept</ListGroup.Item>
                        <ListGroup.Item variant="danger" className="d-flex align-items-center imp">Decline</ListGroup.Item>
                    </ListGroup>
                </Card>
            </CardGroup>
        </div>
        </>
    );
}