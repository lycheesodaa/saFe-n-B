import React, { useState, useEffect } from "react";
import axios from "axios";
import { merge } from 'lodash';
import ReactApexChart from 'react-apexcharts';
// material
import { Card, CardHeader, Box } from '@mui/material';
//
import { BaseOptionChart } from '../../charts';

// ----------------------------------------------------------------------

export default function InfectionSources() {
  const [chartData, setChartData] = useState([
    {
      name: 'Infection Sources',
      data: [],
      dataLabels: {
        enabled: false
      }
    }
  ]);

  const [chartOptions, setChartOptions] = useState(BaseOptionChart, {
    stroke: { width: [0, 2, 3] },
    plotOptions: { bar: { columnWidth: '11%', borderRadius: 4 } },
    fill: {
      type: ['solid'],
      colors: ['#FFC0CB']
    },
    labels: [],
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

  useEffect(() => {
    axios
      .get("http://localhost:8080/covid")
      .then(res => {
        console.log(res.data.infectionSourceList);
        setChartData([
          {
            name: 'Infection Sources',
            data: res.data.infectionSourceList.map(c => c.number),
            dataLabels: {
              enabled: false
            }
          }
        ]);

        setChartOptions({
          stroke: { width: [0, 2, 3] },
          plotOptions: { bar: { columnWidth: '11%', borderRadius: 4 } },
          fill: {
            type: ['solid'],
            colors: ['#FFC0CB']
          },
          labels: res.data.infectionSourceList.map(c => c.infectionSource),
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
      )
      .catch(err => {
        console.log(err);
      }
      );
  }, []);

  return (
    <Card>
      <CardHeader title="Infection Sources" />
      <Box sx={{ p: 3, pb: 1 }} dir="ltr">
        <ReactApexChart type="bar" series={chartData} options={chartOptions} height={364} />
      </Box>
    </Card>
  );
}
