const drawerWidth = 240

export default theme => ({
  menuList: {
    width: '100%',
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper
  },
  drawerPaper: {
    position: 'relative',
    width: drawerWidth
  }
})
