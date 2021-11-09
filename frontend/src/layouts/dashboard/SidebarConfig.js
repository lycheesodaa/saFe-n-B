import { Icon } from '@iconify/react';
import pieChart2Fill from '@iconify/icons-eva/pie-chart-2-fill';
import fileTextFill from '@iconify/icons-eva/file-text-fill';

// ----------------------------------------------------------------------

const getIcon = (name) => <Icon icon={name} width={22} height={22} />;

const sidebarConfig = [
  {
    title: 'COVID-19 Stats',
    path: '/user/stats',
    icon: getIcon(pieChart2Fill)
  },
  {
    title: 'F&B Regulations',
    path: '/user/regulations',
    icon: getIcon("codicon:law")
  },
  {
    title: 'Employee Dashboard',
    path: '/user/employee-dashboard',
    icon: getIcon("bx:bxs-dashboard")
  },
  {
    title: 'Scheduler',
    path: '/user/scheduler',
    icon: getIcon("ic:baseline-schedule")
  },
  {
    title: 'Blog',
    path: '/user/blog',
    icon: getIcon(fileTextFill)
  },
  {
    title: 'Forum',
    path: '/user/forum',
    icon: getIcon("carbon:forum")
  }
  // {
  //   title: 'Not found',
  //   path: '/404',
  //   icon: getIcon(alertTriangleFill)
  // }
];

export default sidebarConfig;
