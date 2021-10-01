import "./MyProfileBorrower.css";
import React from 'react';
import { Line } from 'react-chartjs-2';
import Header from "../Header/HeaderLoginBorrower";

const data = {
  labels: ['Jan 2021', 'Feb 2021', 'March 2021', 'April 2021', 'May 2021', 'June 2021', 'July 2021'],
  datasets: [
    {
      label: 'Credit Score',
      data: [580, 600,550, 700, 720, 850, 830],
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
    <div className='my-profile-borrower'>
      <h1 className = "mt-4 d-flex align-items-center justify-content-center">Credit Score History</h1>
    <Line data={data} options={options} />
    </div>
    <div className='my-profile-borrower'>
      <h1 className = "mt-4 d-flex align-items-center justify-content-center">Current Credit Score</h1>
      <h2 className = "mt-4 d-flex align-items-center justify-content-center">830</h2>
      <h1 className = "mt-4 d-flex align-items-center justify-content-center">#Successful Trades</h1>
      <h2 className = "mt-4 d-flex align-items-center justify-content-center">4</h2>

      
    </div>
    
  </>
);

export default LineChart;