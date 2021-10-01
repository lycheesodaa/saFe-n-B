import React from "react";
import "./MyTradesBorrower.css";
import { Card, CardGroup } from "react-bootstrap";
import { PieChart } from 'react-minimal-pie-chart';
import Header from "../Header/HeaderLoginBorrower";

export default function MyTradesBorrower() {

    return (
        <>
        <Header />
        <div className='my-trades-borrower'>
            <h1 className="mt-4">Requested Trades</h1>
            <CardGroup style={{ marginBottom: 20 + 'px' }}>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        4211
                    </Card.Body>
                </Card>
                {/* <Card className="align-items-center" style={{ borderRadius: 5 + 'px' }}> */}
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You requested for $100,000
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You have received $47,000
                    </Card.Body>
                </Card>
                <Card className="d-flex align-items-center justify-content-center">
                    <Card.Body className="d-flex align-items-center justify-content-center ">
                        <PieChart
                            radius='40'
                            data={[
                                { title: 'One', value: 47, color: '#00FF00' },
                                { title: 'Two', value: 53, color: '#FF0000' }
                            ]}
                        />
                        You have received 47%!
                    </Card.Body>
                </Card>
                <Card bg='danger' text='white' className="align-items-center imp">
                    <Card.Body className="d-flex align-items-center">
                        Cancel Request
                    </Card.Body>
                </Card>
            </CardGroup>

            <CardGroup style={{ marginBottom: 20 + 'px' }}>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        4212
                    </Card.Body>
                </Card>
                {/* <Card className="align-items-center" style={{ borderRadius: 5 + 'px' }}> */}
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You have received $35,000
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        Within 5 months, you will have to repay $37,500
                    </Card.Body>
                </Card>
                <Card bg='success' text='white'className="align-items-center imp" >
                    <Card.Body className="d-flex align-items-center">
                        17 hours to accept
                    </Card.Body>
                </Card>
                <Card bg='danger' text='white' className="align-items-center imp">
                    <Card.Body className="d-flex align-items-center">
                        Cancel Request
                    </Card.Body>
                </Card>
            </CardGroup>

            <CardGroup style={{ marginBottom: 20 + 'px' }}>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        4213
                    </Card.Body>
                </Card>
                {/* <Card className="align-items-center" style={{ borderRadius: 5 + 'px' }}> */}
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You requested for $50,000
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You have received $0
                    </Card.Body>
                </Card>
                <Card className="d-flex align-items-center justify-content-center">
                    <Card.Body className="d-flex align-items-center justify-content-center ">
                        <PieChart
                            radius='40'
                            data={[
                                { title: 'One', value: 0, color: '#00FF00' },
                                { title: 'Two', value: 100, color: '#FF0000' }
                            ]}
                        />
                        You have received 0%!
                    </Card.Body>
                </Card>
                <Card bg='danger' text='white' className="align-items-center imp">
                    <Card.Body className="d-flex align-items-center">
                        Cancel Request
                    </Card.Body>
                </Card>
            </CardGroup>

            <h1 className="mt-4">Pending Trades</h1>
            <CardGroup style={{ marginBottom: 20 + 'px' }}>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        3641
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You borrowed $40,000
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You have to return $30,000
                    </Card.Body>
                </Card>
                <Card className="d-flex align-items-center justify-content-center">
                    <Card.Body className="d-flex align-items-center justify-content-center ">
                        <PieChart
                            radius='40'
                            data={[
                                { title: 'One', value: 25, color: '#00FF00' },
                                { title: 'Two', value: 75, color: '#FF0000' }
                            ]}
                        />
                        You are 25% there!
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        37 days to pay
                    </Card.Body>
                </Card>
                <Card bg='success' text='white'className="align-items-center imp" >
                    <Card.Body className="d-flex align-items-center">
                        Pay now 
                    </Card.Body>
                </Card>
            </CardGroup>
            <h1 className="mt-4">Completed Trades</h1>
            <CardGroup style={{ marginBottom: 20 + 'px' }}>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        2742
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You borrowed $30,000
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You returned $30,000
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        Trade closed on 01/04/2021.
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        This changed your credit card rating by +10.
                    </Card.Body>
                </Card>
            </CardGroup>
            </div>
            </>
        );
}