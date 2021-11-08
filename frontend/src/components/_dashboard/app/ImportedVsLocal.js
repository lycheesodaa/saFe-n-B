import axios from "axios";
import ReactApexChart from 'react-apexcharts';
import React, { useEffect, useState } from "react";
// material
import { useTheme, styled } from '@mui/material/styles';
import { Card, CardHeader } from '@mui/material';
// utils
import { fNumber } from '../../../utils/formatNumber';

import { connect } from "react-redux";

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


function ImportedVsLocal({ scrape }) {
  const theme = useTheme();

  const [chartSeries, setChartSeries] = useState([]);

  useEffect(() => {
    if ("id" in scrape) {
      setChartSeries([scrape.localTransmissions, scrape.importedCases, scrape.importedOrLocalUnreportedCases]);
    }
  }, [scrape])

  const chartOptions = {
    colors: [
      theme.palette.secondary.main,
      theme.palette.success.main,
      theme.palette.error.main
    ],
    labels: ['Local', 'Imported', 'Unreported'],
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

  return (
    <Card>
      <CardHeader title="Imported v/s Local" />
      <ChartWrapperStyle dir="ltr">
        <ReactApexChart type="pie" series={chartSeries} options={chartOptions} height={280} />
      </ChartWrapperStyle>
    </Card>
  );
}

const mapStateToProps = (state) => ({
  scrape: state.scrape.scrapedData
})

export default connect(mapStateToProps, null)(ImportedVsLocal);