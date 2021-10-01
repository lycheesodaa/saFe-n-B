import "./MyProfileLender.css";
import React from 'react';
import { Line } from 'react-chartjs-2';
import Header from "../Header/HeaderLoginLender";

const data = {
  labels: ['Jan 2021', 'Feb 2021', 'March 2021', 'April 2021', 'May 2021', 'June 2021'],
  datasets: [
    {
      label: 'Profit/Loss',
      data: [0, 90,280, 320, 100, -70],
      fill: false,
      backgroundColor: '#00008B',
      borderColor: '#00008B',
    },
  ],
};

const options = {
  scales: {
    yAxes: [
      {
        ticks: {
          beginAtZero: false,
        },
      },
    ],
  },
};

const LineChart = () => (
  <>
  <Header />
    <div className='my-profile-lender'>
      <h1 className = "mt-4 d-flex align-items-center justify-content-center">Net Profit History</h1>
    <Line data={data} options={options} />
    </div>
    <div className='my-profile-lender'>
      <h1 className = "mt-4 d-flex align-items-center justify-content-center">Net Profit</h1>
      <h2 className = "mt-4 d-flex align-items-center justify-content-center">-70</h2>
      <h1 className = "mt-4 d-flex align-items-center justify-content-center">#Successful Trades</h1>
      <h2 className = "mt-4 d-flex align-items-center justify-content-center">9</h2>

    </div>
    
  </>
);

export default LineChart;