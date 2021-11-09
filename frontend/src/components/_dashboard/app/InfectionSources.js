import React, { useState, useEffect } from "react";
import axios from "axios";
import { merge } from 'lodash';
import ReactApexChart from 'react-apexcharts';
// material
import { Card, CardHeader, Box } from '@mui/material';
//
import { BaseOptionChart } from '../../charts';

import { connect } from "react-redux";

// ----------------------------------------------------------------------

function InfectionSources({ scrape }) {

  const [chartData, setChartData] = useState([]);
  const [chartOptions, setChartOptions] = useState({});

  useEffect(() => {
    if ("id" in scrape) {
      setChartData([
        {
          name: 'Infection Sources',
          data: scrape.infectionSourceList.map(c => c.number),
          dataLabels: {
            enabled: false
          }
        }
      ])
      setChartOptions({
        stroke: { width: [0, 2, 3] },
        plotOptions: {
          bar: { columnWidth: '11%', borderRadius: 4 }
        },
        fill: {
          type: ['solid'],
          colors: ['#FFC0CB']
        },
        labels: scrape.infectionSourceList.map(c => c.infectionSource),
        tooltip: {
          shared: true,
          intersect: false,
          y: {
            formatter: (y) => {
              if (typeof y !== 'undefined') {
                return `${y.toFixed(0)} people`;
              }
              return y;
            }
          }
        }
      });
    }
  }, [scrape])

  return (
    <Card>
      <CardHeader title="Infection Sources" />
      <Box sx={{ p: 3, pb: 1 }} dir="ltr">
        <ReactApexChart type="bar" series={chartData} options={chartOptions} height={364} />
      </Box>
    </Card>
  );
}

const mapStateToProps = (state) => ({
  scrape: state.scrape.scrapedData
})

export default connect(mapStateToProps, null)(InfectionSources);
