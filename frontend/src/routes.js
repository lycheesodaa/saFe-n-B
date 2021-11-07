import { Navigate, useRoutes } from 'react-router-dom';
// layouts
import DashboardLayout from './layouts/dashboard';
import LogoOnlyLayout from './layouts/LogoOnlyLayout';
//
import Login from './pages/Login';
import Register from './pages/Register';
import DashboardApp from './pages/DashboardApp';
import Products from './pages/Products';
import Blog from './pages/Blog';
import User from './pages/User';
import NotFound from './pages/Page404';
import Regulations from './pages/Regulations';

// ----------------------------------------------------------------------

export default function Router() {
  return useRoutes([
    {
      path: '/user',
      element: <DashboardLayout />,
      children: [
        { element: <Navigate to="/user/stats" replace /> },
        { path: 'stats', element: <DashboardApp /> },
        { path: 'regulations', element: <Regulations /> },
        { path: 'scheduler', element: <NotFound /> },
        { path: 'blog', element: <NotFound /> },
        { path: 'forum', element: <NotFound /> }
      ]
    },
    {
      path: '/',
      element: <LogoOnlyLayout />,
      children: [
        { path: 'login', element: <Login /> },
        { path: 'register', element: <Register /> },
        { path: '404', element: <NotFound /> },
        { path: '/', element: <Navigate to="/user" /> },
        { path: '*', element: <Navigate to="/404" /> }
      ]
    },
    { path: '*', element: <Navigate to="/404" replace /> }
  ]);
}
