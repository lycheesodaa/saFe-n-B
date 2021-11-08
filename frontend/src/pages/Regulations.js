// material
import React, { useState, useEffect } from "react";
import { Box, Grid, Container, Typography } from '@mui/material';
// components
import Page from '../components/Page';
// ----------------------------------------------------------------------
import { connect } from "react-redux";
function Regulations({scrape}) {
    const [regulations, setRegulations] = useState("");

    useEffect(() => {
        if ("id" in scrape) {
            setRegulations(scrape.regulations);
        }
    }, [scrape]);
    var temp = regulations
    var head1 = temp.match(/Food(.*?)Establishments/g)
    var body1 = temp.substring(temp.indexOf("Establishment") + 14, temp.indexOf("Latest"))
    var head2 = temp.match(/Latest(.*?)establishments/g)
    var body2 = temp.substring(temp.indexOf("for F&B establishments") + 22, temp.indexOf("Vaccination"))
    var head3 = temp.match(/Vaccination(.*?)VDS\)/g)
    var body3 = temp.substring(temp.indexOf("Measures (VDS)") + 15, temp.indexOf("COVID-19 testing"))
    var head4 = temp.match(/COVID-19 testing/g)
    var body4 = temp.substring(temp.indexOf("COVID-19 testing") + 17, temp.indexOf("Alcohol"))
    var head5 = temp.match(/Alcohol (.*?) Events/g)
    var body5 = temp.substring(temp.indexOf("Events") + 7, temp.indexOf("Protocol"))
    var head6 = temp.match(/Protocol on handling COVID-19 cases/g)
    var body6 = temp.substring(temp.indexOf("handling COVID-19 cases") + 25, temp.indexOf("Safe Management Measures – Customer"))
    var head7 = temp.match(/Safe Management Measures (.*?)front-of-house/g)
    var body7 = temp.substring(temp.indexOf("Safe Management Measures –") + 68, temp.indexOf("Queue"))
    var head8 = temp.match(/Queue management/g)
    var body8 = temp.substring(temp.indexOf("Queue management") + 17, temp.indexOf("Table"))
    var head9 = temp.match(/Table and seating management/g)
    var body9 = temp.substring(temp.indexOf("seating management") + 19, temp.indexOf("Entertainment and music"))
    var head10 = temp.match(/Entertainment and music/g)
    var body10 = temp.substring(temp.indexOf("and music") + 9, temp.indexOf("Contact"))
    var head11 = temp.match(/Contact tracing/g)
    var body11 = temp.substring(temp.indexOf("Contact tracing") + 16, temp.indexOf("Cleanliness"))
    var head12 = temp.match(/Cleanliness and hygiene/g)
    var body12 = temp.substring(temp.indexOf("Cleanliness and hygiene") + 23, temp.indexOf("Food lines served by workers"))
    var head13 = temp.match(/Food lines served by workers28/g)
    var body13 = temp.substring(temp.indexOf("28") + 3, temp.indexOf("Ventilation"))
    var head14 = temp.match(/Ventilation and improving indoor air quality/g)
    var body14 = temp.substring(temp.indexOf("air quality") + 12, temp.indexOf("Safe Management Measures – Workplace premises31/back of house/kitchen"))
    var head15 = temp.match(/Safe Management Measures (.*?)kitchen/g)
    var body15 = temp.substring(temp.indexOf("house/kitchen") + 14, temp.indexOf("Enforcement"))
    var head16 = temp.match(/Enforcement of measures/g)
    var body16 = temp.substring(temp.indexOf("Enforcement of measures") + 25, temp.indexOf("Resources"))
    var head17 = temp.match(/Resources/g)
    var body17 = temp.substring(temp.indexOf("Resources") + 10)

    return (
        <Page title="Regulations">
            <Container maxWidth="xl">
                <center><h1>Covid Regulations</h1></center>
                <h2>{head1}</h2>
                <p>{body1}</p>
                <h2 style={{ fontSize: 30 }}>{head2} :</h2>
                <p>{body2}</p>
                <h2 style={{ fontSize: 20 }}>{head3}</h2>
                <p>{body3}</p>
                <h2 style={{ fontSize: 20 }}>{head4}</h2>
                <p>{body4}</p>
                <h2 style={{ fontSize: 20 }}>{head5}</h2>
                <p>{body5}</p>
                <h2 style={{ fontSize: 20 }}>{head6}</h2>
                <p>{body6}</p>
                <h2 style={{ fontSize: 30 }}>{head7} :</h2>
                <p>{body7}</p>
                <h2 style={{ fontSize: 20 }}>{head8}</h2>
                <p>{body8}</p>
                <h2 style={{ fontSize: 20 }}>{head9}</h2>
                <p>{body9}</p>
                <h2 style={{ fontSize: 20 }}>{head10}</h2>
                <p>{body10}</p>
                <h2 style={{ fontSize: 20 }}>{head11}</h2>
                <p>{body11}</p>
                <h2 style={{ fontSize: 20 }}>{head12}</h2>
                <p>{body12}</p>
                <h2 style={{ fontSize: 20 }}>Food lines served by workers</h2>
                <p>{body13}</p>
                <h2 style={{ fontSize: 20 }}>{head14}</h2>
                <p>{body14}</p>
                <h2 style={{ fontSize: 30 }}>Safe Management Measures – Workplace premises/back of house/kitchen :</h2>
                <p >{body15}</p>
                <h2 style={{ fontSize: 30 }}>{head16} :</h2>
                <p>{body16}</p>
                {/* <h2 style={{ fontSize: 30 }}>{head17} :</h2>
                <p>{body17}</p> */}

            </Container>
        </Page>
    )

}

const mapStateToProps = (state) => ({
    scrape: state.scrape.scrapedData
  })
  
  export default connect(mapStateToProps, null)(Regulations);
