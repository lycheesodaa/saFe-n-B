import React, { useEffect } from "react";
import axios from "axios";
import ReactApexChart from 'react-apexcharts';
// material
import { Box, Card, CardHeader } from '@mui/material';
// utils
import { fNumber } from '../../../utils/formatNumber';
//
import { useState } from 'react';

// ----------------------------------------------------------------------



export default function Nationality() {

  const [chartData, setChartData] = useState([{ name: "people", data: [400, 430, 448, 470, 540, 580, 690, 1100, 1200, 1380] }]);
  const [chartOptions, setChartOptions] = useState({
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
      categories: []
    }
  });
  useEffect(() => {
    axios
      .get("http://localhost:8080/covid")
      .then(res => {
        setChartData([{
          name: "people",
          data: res.data.nationalityList.map(c => c.number)
        }])
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
            categories: res.data.nationalityList.map(c => c.nationality)
          }
        })
      }
      )
      .catch(err => {
        console.log(err);
      }
      );
  }, []);

  return (
    <Card>
      <CardHeader title="Nationality" />
      <Box sx={{ mx: 3 }} dir="ltr">
        <ReactApexChart type="bar" series={chartData} options={chartOptions} height={364} />
      </Box>
    </Card>
  );
}
