import React from "react";
import { Carousel } from "react-bootstrap";
import Header from "../Header/HeaderLogout";
//import "./Home.css";

export default function Home() {
    return (
        <>
            <Header />
            <div className="my-profile-borrower">
                <h4 style={{ color: "#000080" }} className="d-flex align-items-center justify-content-center">A platform to connect Borrowers and Lenders in a bid to provide financial capital to Borrowers and an avenue of interest income for Lenders
                </h4>

                <h4 style={{ color: "#000080" }} className="d-flex align-items-center justify-content-center">A platform to connect Borrowers and Lenders in a bid to provide financial capital to Borrowers and an avenue of interest income for Lenders
                </h4>

            </div>

            {/* <Carousel>
                <Carousel.Item interval={5000} variation="dark">
                    <img
                        className="d-block w-100"
                        src="bg3.jpeg"
                        alt="Second slide"
                        style={{ height: "600px" }}
                    />
                    <Carousel.Caption>
                        <h4 style={{ color: "#000080" }}>A platform to connect Borrowers and Lenders in a bid to provide financial capital to Borrowers and an avenue of interest income for Lenders
                        </h4>
                    </Carousel.Caption>
                </Carousel.Item>
                <Carousel.Item interval={5000}>
                    <img
                        className="d-block w-100"
                        src="bg6.jpeg"
                        alt="First slide"
                        style={{ height: "600px" }}
                    />
                    <Carousel.Caption>
                        <h4 style={{ color: "black" }}>Encouraging financial inclusion by facilitating access to capital and helping to create economic opportunities around the world through Goldman Sachs</h4>
                    </Carousel.Caption>
                </Carousel.Item>
            </Carousel> */}
        </>);
}