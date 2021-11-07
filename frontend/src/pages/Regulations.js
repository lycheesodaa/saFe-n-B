// material
import React, { useState, useEffect } from "react";
import { Box, Grid, Container, Typography } from '@mui/material';
// components
import Page from '../components/Page';


// ----------------------------------------------------------------------
import axios from "axios";

export default function Regulations() {
    const [covidData, setCovidData] = useState({
        regulations: 0,
        averageAge: "",
        clusterList: [],
        created_at: "",
        critical: "",
        deceased: "",
        discharged: "",
        femaleCases: "",
        genderUnidentifiedCases: "",
        id: "",
        importedCases: "",
        importedOrLocalUnreportedCases: "",
        infectionSourceList: "",
        localTransmissions: "",
        maleCases: "",
        nationalityList: [],
        regulations: "",
        totalCases: ""
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
        <Page title="Regulations">
            <Container maxWidth="xl">
                <center><h1>This page is still in progress</h1></center>
                <p>{covidData.regulations}</p>
            </Container>
        </Page>
    )
}

