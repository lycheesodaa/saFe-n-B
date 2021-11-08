import React, { useState, useEffect } from "react";
import axios from "axios";
import { Icon } from '@iconify/react';
// material
import { alpha, styled } from '@mui/material/styles';
import { Card, Typography } from '@mui/material';
// utils
import { fInternationalNumber } from '../../../utils/formatNumber';

import { connect } from "react-redux";

// ----------------------------------------------------------------------

const RootStyle = styled(Card)(({ theme }) => ({
  boxShadow: 'none',
  textAlign: 'center',
  padding: theme.spacing(5, 0),
  color: theme.palette.error.darker,
  backgroundColor: theme.palette.error.lighter
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
  color: theme.palette.error.dark,
  backgroundImage: `linear-gradient(135deg, ${alpha(theme.palette.error.dark, 0)} 0%, ${alpha(
    theme.palette.error.dark,
    0.24
  )} 100%)`
}));

// ----------------------------------------------------------------------



function CriticalCases({scrape}) {

  const [criticalCases, setCriticalCases] = useState(0);

  useEffect(() => {
    if ("id" in scrape) {
      setCriticalCases(scrape.critical);
    }
  }, [scrape])


  return (
    <RootStyle>
      <IconWrapperStyle>
        <Icon icon="bi:exclamation-circle-fill" width={24} height={24} />
      </IconWrapperStyle>
      <Typography variant="h3">{fInternationalNumber(criticalCases)}</Typography>
      <Typography variant="subtitle2" sx={{ opacity: 0.72 }}>
        Critical Cases
      </Typography>
    </RootStyle>
  );
}

const mapStateToProps = (state) => ({
  scrape: state.scrape.scrapedData
})

export default connect(mapStateToProps, null)(CriticalCases);