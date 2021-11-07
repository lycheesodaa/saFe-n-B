import axios from "axios";
import ReactApexChart from 'react-apexcharts';
import React, { useEffect, useState } from "react";
// material
import { useTheme, styled } from '@mui/material/styles';
import { Card, CardHeader } from '@mui/material';
// utils
import { fNumber } from '../../../utils/formatNumber';
//
import { BaseOptionChart } from '../../charts';

// ----------------------------------------------------------------------

const CHART_HEIGHT = 372;
const LEGEND_HEIGHT = 72;

const ChartWrapperStyle = styled('div')(({ theme }) => ({
  height: CHART_HEIGHT,
  marginTop: theme.spacing(5),
  '& .apexcharts-canvas svg': { height: CHART_HEIGHT },
  '& .apexcharts-canvas svg,.apexcharts-canvas foreignObject': {
    overflow: 'visible'
  },
  '& .apexcharts-legend': {
    height: LEGEND_HEIGHT,
    alignContent: 'center',
    position: 'relative !important',
    borderTop: `solid 1px ${theme.palette.divider}`,
    top: `calc(${CHART_HEIGHT - LEGEND_HEIGHT}px) !important`
  }
}));

// ----------------------------------------------------------------------


export default function GenderDistribution() {
  const theme = useTheme();

  const [chartData, setChartData] = useState([])
  const chartOptions = {
    colors: [
      theme.palette.primary.main,
      theme.palette.info.main,
      theme.palette.warning.main
    ],
    labels: ['Male', 'Female', 'Unidentified'],
    stroke: { colors: [theme.palette.background.paper] },
    legend: {
      floating: true,
      show: true,
      fontSize: 13,
      position: 'top',
      horizontalAlign: 'center',
      markers: {
        radius: 12
      },
      fontWeight: 500,
      itemMargin: { horizontal: 12 },
      labels: {
        colors: theme.palette.text.primary
      }
    },
    dataLabels: { enabled: true, dropShadow: { enabled: false } },
    tooltip: {
      fillSeriesColor: false,
      y: {
        formatter: (seriesName) => fNumber(seriesName),
        title: {
          formatter: (seriesName) => `#${seriesName}`
        }
      }
    },
    plotOptions: {
      pie: { donut: { labels: { show: false } } }
    }
  };

  useEffect(() => {
    axios
      .get("http://localhost:8080/covid")
      .then(res => {
        setChartData([res.data.maleCases, res.data.femaleCases, res.data.genderUnidentifiedCases]);
      }
      )
      .catch(err => {
        console.log(err);
      }
      );
  }, []);

  return (
    <Card>
      <CardHeader title="Gender Distribution" />
      <ChartWrapperStyle dir="ltr">
        <ReactApexChart type="pie" series={chartData} options={chartOptions} height={280} />
      </ChartWrapperStyle>
    </Card>
  );
}
