import React from "react";
import "./MyTradesLender.css";
import { Card, CardGroup } from "react-bootstrap";
import { PieChart } from 'react-minimal-pie-chart';
import Header from "../Header/HeaderLoginLender";

export default function MyTradesower() {

    return (
        <>
        <Header />
        <div className='my-trades-lender'>
            <h1 className="mt-4">Pending Trades</h1>
            <CardGroup style={{ marginBottom: 20 + 'px' }}>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        3641
                    </Card.Body>
                </Card>
                {/* <Card className="align-items-center" style={{ borderRadius: 5 + 'px' }}> */}
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You lent $80,000
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You will receive $85,000
                    </Card.Body>
                </Card>
                <Card className="d-flex align-items-center justify-content-center">
                    <Card.Body className="d-flex align-items-center justify-content-center ">
                        <PieChart
                            radius='40'
                            data={[
                                { title: 'One', value: 72, color: '#00FF00' },
                                { title: 'Two', value: 28, color: '#FF0000' }
                            ]}
                        />
                        You have received 72%!
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        21 days to completion
                    </Card.Body>
                </Card>
            </CardGroup>

            <CardGroup style={{ marginBottom: 20 + 'px' }}>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        3642
                    </Card.Body>
                </Card>
                {/* <Card className="align-items-center" style={{ borderRadius: 5 + 'px' }}> */}
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You lent $12,000
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You will receive $14,000
                    </Card.Body>
                </Card>
                <Card className="d-flex align-items-center justify-content-center">
                    <Card.Body className="d-flex align-items-center justify-content-center ">
                        <PieChart
                            radius='40'
                            data={[
                                { title: 'One', value: 94, color: '#00FF00' },
                                { title: 'Two', value: 6, color: '#FF0000' }
                            ]}
                        />
                        You have received 94%!
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        19 days to completion
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
                        You received $108,000
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        You earned 8% in interest
                    </Card.Body>
                </Card>
                <Card className="align-items-center" >
                    <Card.Body className="d-flex align-items-center">
                        Trade closed on 04/04/2021
                    </Card.Body>
                </Card>
            </CardGroup>
            </div>
            </>
        );
}