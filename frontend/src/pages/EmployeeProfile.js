import { filter } from 'lodash';
import { Icon } from '@iconify/react';
import { sentenceCase } from 'change-case';
import { useEffect, useState } from 'react';
import plusFill from '@iconify/icons-eva/plus-fill';
import { Link as RouterLink } from 'react-router-dom';
// material
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
//

import { connect } from 'react-redux';
import { getEmployeesByFirmEmail } from "../actions/firmActions";
import { useNavigate, useParams } from 'react-router-dom';

// ----------------------------------------------------------------------


function EmployeeProfile({ employees, getEmployeesByFirmEmail, user }) {
    console.log("params")
    let { email } = useParams();

    return (
        <Page title="Employee Dashboard">
            <Container>
                <Stack direction="row" alignItems="center" justifyContent="space-between" mb={5}>
                    <Typography variant="h4" gutterBottom>
                        Employee {email}
                    </Typography>
                </Stack>


            </Container>
        </Page>
    );
}

const mapStateToProps = (state) => ({
    employees: state.firm.employees,
    user: state.auth.user
})

export default connect(mapStateToProps, { getEmployeesByFirmEmail })(EmployeeProfile);
