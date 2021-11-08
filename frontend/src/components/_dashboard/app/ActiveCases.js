import React, { useState, useEffect } from "react";
import { Icon } from '@iconify/react';
// material
import { alpha, styled } from '@mui/material/styles';
import { Card, Typography } from '@mui/material';
// utils
import { fInternationalNumber } from '../../../utils/formatNumber';
import axios from "axios";
import { connect } from "react-redux";
import {getScrapedData} from "../../../actions/scrapeActions";

// ----------------------------------------------------------------------

const RootStyle = styled(Card)(({ theme }) => ({
  boxShadow: 'none',
  textAlign: 'center',
  padding: theme.spacing(5, 0),
  color: theme.palette.info.darker,
  backgroundColor: theme.palette.info.lighter
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
  color: theme.palette.info.dark,
  backgroundImage: `linear-gradient(135deg, ${alpha(theme.palette.info.dark, 0)} 0%, ${alpha(
    theme.palette.info.dark,
    0.24
  )} 100%)`
}));

// ----------------------------------------------------------------------

const TOTAL = 1352831;

function ActiveCases({scrape, getScrapedData}) {

  useEffect(() => {
    getScrapedData();
  }, [])

  const [activeCases, setActiveCases] = useState(0);

  useEffect(() => {
    if ("id" in scrape) {
      setActiveCases(activeCases);
    }
  }, [scrape]) 


  return (
    <RootStyle>
      <IconWrapperStyle>
        <Icon icon="uit:covid-19" width={24} height={24} />
      </IconWrapperStyle>
      <Typography variant="h3">{fInternationalNumber(scrape.activeCases)}</Typography>
      <Typography variant="subtitle2" sx={{ opacity: 0.72 }}>
        Active Cases
      </Typography>
    </RootStyle>
  );
}

const mapStateToProps = (state) => ({
  scrape: state.scrape.scrapedData
})

export default connect(mapStateToProps, {getScrapedData})(ActiveCases);
