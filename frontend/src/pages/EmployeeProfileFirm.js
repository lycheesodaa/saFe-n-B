import { filter } from 'lodash';
import { Icon } from '@iconify/react';
import { sentenceCase } from 'change-case';
import { useEffect, useState } from 'react';
import plusFill from '@iconify/icons-eva/plus-fill';
import { Link as RouterLink } from 'react-router-dom';
// material
import { ThemeProvider, createTheme } from '@material-ui/core/styles';
import {
    Card,
    Table,
    Stack,
    Avatar,
    Button,
    Checkbox,
    TableRow,
    TableBody,
    TableCell,
    Container,
    Typography,
    TableContainer,
    TablePagination
} from '@mui/material';
// components
import Page from '../components/Page';
import ARTTable from '../components/ARTTable';
import TempTable from '../components/TempTable';
//
import { connect } from 'react-redux';
import { getEmployeesByFirmEmail } from "../actions/firmActions";
import { useNavigate, useParams } from 'react-router-dom';

// ----------------------------------------------------------------------


function EmployeeProfileFirm({ employees, getEmployeesByFirmEmail, user }) {
    const [thisEmployee, setThisEmployee] = useState({
        "address": "Room No. 707, YMCA,",
        "artList": [],
        "contact": "2193821093",
        "dateOfBirth": "02/11/2021",
        "email": "employee1@email.com",
        "name": "employee 1",
        "nric": "dsnj3",
        "password": "dGVzdDEyMw==",
        "tempList": [],
        "vacinnates": false,
    });
    let { email } = useParams();
    useEffect(() => {
        for (var i = 0; i < employees.length; i++) {
            if (email == employees[i].email) {
                setThisEmployee(employees[i]);
            }
        }
    }, [])
    const colortheme = createTheme({
        palette: {
            primary: { main: "#e91e63", contrastText: "#000" },
            secondary: { main: "#c8facd", contrastText: "#000" }
        }
    });

    return (
        <Page title="Employee Dashboard">
            <ThemeProvider theme={colortheme}>
                <Container>
                    <Typography variant="h1" sx={{ mb: 2 }} color="secondary">
                        Employee Profile
                    </Typography>
                    <Typography variant="h4" sx={{ mb: 2 }} color="secondary">
                        Basic Details
                    </Typography>
                    <Card sx={{ p: 5 }}>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Name: {thisEmployee.name}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Email: {thisEmployee.email}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Contact: {thisEmployee.contact}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Date Of Birth: {thisEmployee.dateOfBirth}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            NRIC/FIN: {thisEmployee.nric}
                        </Typography>
                        <Typography variant="h6" sx={{ mb: 2 }} color="primary">
                            Address: {thisEmployee.address}
                        </Typography>
                    </Card>
                </Container>
                <Container>
                    <Typography variant="h4" sx={{ mb: 2, mt: 2 }} color="secondary">
                        ART History
                    </Typography>
                    <ARTTable artList={thisEmployee.artList} />
                </Container>
                <Container>
                    <Typography variant="h4" sx={{ mb: 2, mt: 2 }} color="secondary">
                        Temperature History
                    </Typography>
                    <TempTable tempList={thisEmployee.tempList} />
                </Container>
            </ThemeProvider>
        </Page>
    );
}

const mapStateToProps = (state) => ({
    employees: state.firm.employees,
    user: state.auth.user
})

export default connect(mapStateToProps, { getEmployeesByFirmEmail })(EmployeeProfileFirm);
