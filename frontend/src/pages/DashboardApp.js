// material
import { Box, Grid, Container, Typography } from '@mui/material';
// components
import Page from '../components/Page';
import {
  DeceasedCases,
  Clusters,
  Nationality,
  AppCurrentSubject,
  GenderDistribution,
  CriticalCases,
  AppNewsUpdate,
  ActiveCases,
  AppOrderTimeline,
  AppTasks,
  AppTrafficBySite,
  InfectionSources,
  TotalCases,
  ImportedVsLocal
} from '../components/_dashboard/app';


// ----------------------------------------------------------------------

export default function DashboardApp() {
  return (
    <Page title="Regulations">
      <Container maxWidth="xl">
        <Box sx={{ pb: 5 }}>
          <Typography variant="h4">Hi, Welcome back</Typography>
        </Box>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6} md={3}>
            <TotalCases />
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <ActiveCases />
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <CriticalCases />
          </Grid>
          <Grid item xs={12} sm={6} md={3}>
            <DeceasedCases />
          </Grid>

          <Grid item xs={12} md={6} lg={8}>
            <InfectionSources />
          </Grid>

          <Grid item xs={12} md={6} lg={4}>
            <GenderDistribution />
          </Grid>

          <Grid item xs={12} md={6} lg={4}>
            <ImportedVsLocal />
          </Grid>

          <Grid item xs={12} md={6} lg={8}>
            <Nationality />
          </Grid>

          {/* <Grid item xs={12} md={6} lg={8}>
            <Clusters />
          </Grid>

          <Grid item xs={12} md={6} lg={4}>
            <AppCurrentSubject />
          </Grid>

          <Grid item xs={12} md={6} lg={8}>
            <AppNewsUpdate />
          </Grid>

          <Grid item xs={12} md={6} lg={4}>
            <AppOrderTimeline />
          </Grid>

          <Grid item xs={12} md={6} lg={4}>
            <AppTrafficBySite />
          </Grid>

          <Grid item xs={12} md={6} lg={8}>
            <AppTasks />
          </Grid> */}
        </Grid>
      </Container>
    </Page>
  );
}
