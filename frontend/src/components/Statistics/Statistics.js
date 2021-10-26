import React, { useState, useEffect } from "react";
import axios from "axios";
import { Card, CardGroup } from "react-bootstrap";
import "./Statistics.css";

export default function Statistics() {

    const internationalNumberFormat = new Intl.NumberFormat('en-US')

    const [covidData, setCovidData] = useState({
        // regulations: 0,
        averageAge: "",
        clusterList: [],
        created_at: "",
        critical: "",
        deceased: "",
        discharged: "",
        femaleCases: "",
        genderUnidentifiedCases: "",
        // id: "",
        importedCases: "",
        importedOrLocalUnreportedCases: "",
        infectionSourceList: "",
        localTransmissions: "",
        maleCases: "",
        nationalityList: [],
        regulations: "",
        // totalCases: ""
    });

    useEffect(() => {
        axios
            .get("http://localhost:8080/covid")
            .then(res => {
                setCovidData(res.data);
            }
            )
            .catch(err => {
                console.log(err);
            }
            );
    }, []);

    return (
        <div className="stats-div">
            <div class="MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation0 MuiCard-root css-1e8uo1c">
                Total Cases
            </div>
            <CardGroup>
                <Card style={{ width: '18rem' }} className="mt-4 ">
                    <Card.Body>
                        <h2>{internationalNumberFormat.format(covidData.totalCases)}</h2>
                        <Card.Text>
                            Total Cases
                        </Card.Text>
                    </Card.Body>
                </Card>
                <Card style={{ width: '18rem' }} className="mt-4 ">
                    <Card.Body>
                        <h2>{internationalNumberFormat.format(covidData.activeCases)}</h2>
                        <Card.Text>
                            Active Cases
                        </Card.Text>
                    </Card.Body>
                </Card>
                <Card style={{ width: '18rem' }} className="mt-4 ">
                    <Card.Body>
                        <h2>{covidData.activeCases}</h2>
                        <Card.Text>
                            Active Cases
                        </Card.Text>
                    </Card.Body>
                </Card>
                <Card style={{ width: '18rem' }} className="mt-4 ">
                    <Card.Body>
                        <h2>{covidData.maleCases}</h2>
                        <Card.Text>
                            Active Cases
                        </Card.Text>
                    </Card.Body>
                </Card>
            </CardGroup>
        </div>
    )
}