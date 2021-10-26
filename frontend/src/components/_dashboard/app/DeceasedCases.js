import React, { useState, useEffect } from "react";
import axios from "axios";
import { Icon } from '@iconify/react';
// material
import { alpha, styled } from '@mui/material/styles';
import { Card, Typography } from '@mui/material';
// utils
import { fInternationalNumber } from '../../../utils/formatNumber';

// ----------------------------------------------------------------------

const RootStyle = styled(Card)(({ theme }) => ({
  boxShadow: 'none',
  textAlign: 'center',
  padding: theme.spacing(5, 0),
  color: theme.palette.warning.darker,
  backgroundColor: theme.palette.warning.lighter
}));

const IconWrapperStyle = styled('div')(({ theme }) => ({
  margin: 'auto',
  display: 'flex',
  borderRadius: '50%',
  alignItems: 'center',
  width: theme.spacing(8),
  height: theme.spacing(8),
  justifyContent: 'center',
  marginBottom: theme.spacing(3),
  color: theme.palette.warning.dark,
  backgroundImage: `linear-gradient(135deg, ${alpha(theme.palette.warning.dark, 0)} 0%, ${alpha(
    theme.palette.warning.dark,
    0.24
  )} 100%)`
}));

// ----------------------------------------------------------------------



export default function DeceasedCases() {
  const [covidData, setCovidData] = useState({
    activeCases: "",
    averageAge: "",
    clusterList: "",
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
    nationalityList: "",
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
    <RootStyle>
      <IconWrapperStyle>
        <Icon icon="healthicons:death-alt2" width={24} height={24} />
      </IconWrapperStyle>
      <Typography variant="h3">{fInternationalNumber(covidData.deceased)}</Typography>
      <Typography variant="subtitle2" sx={{ opacity: 0.72 }}>
        Deceased Cases
      </Typography>
    </RootStyle>
  );
}
