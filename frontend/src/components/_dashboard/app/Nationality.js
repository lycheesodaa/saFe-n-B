import React, { useEffect } from "react";
import axios from "axios";
import ReactApexChart from 'react-apexcharts';
// material
import { Box, Card, CardHeader } from '@mui/material';
// utils
import { fNumber } from '../../../utils/formatNumber';
//
import { useState } from 'react';

import { connect } from "react-redux";

// ----------------------------------------------------------------------



function Nationality({ scrape }) {

  const [chartData, setChartData] = useState([]);
  const [chartOptions, setChartOptions] = useState({});

  useEffect(() => {
    if ("id" in scrape) {
      setChartData([{
        name: "people",
        data: scrape.nationalityList.map(c => c.number)
      }]);
      setChartOptions({
        tooltip: {
          marker: { show: false },
          y: {
            formatter: (seriesName) => fNumber(seriesName),
            title: {
              formatter: (seriesName) => `#${seriesName}`
            }
          }
        },
        plotOptions: {
          bar: { horizontal: true, barHeight: '28%', borderRadius: 2 }
        },
        xaxis: {
          categories: scrape.nationalityList.map(c => c.nationality)
        }
      });
    }
  }, [scrape])

  return (
    <Card>
      <CardHeader title="Nationality" />
      <Box sx={{ mx: 3 }} dir="ltr">
        <ReactApexChart type="bar" series={chartData} options={chartOptions} height={364} />
      </Box>
    </Card>
  );
}

const mapStateToProps = (state) => ({
  scrape: state.scrape.scrapedData
})

export default connect(mapStateToProps, null)(Nationality);