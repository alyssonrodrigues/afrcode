import React, { Component } from 'react'
import { connect } from 'react-redux'
import { BrowserRouter, Link } from 'react-router-dom'
import classNames from 'classnames'
import { withStyles } from 'material-ui/styles'
import AppBar from 'material-ui/AppBar'
import Toolbar from 'material-ui/Toolbar'
import Typography from 'material-ui/Typography'
import Button from 'material-ui/Button'
import IconButton from 'material-ui/IconButton'
import MenuIcon from '@material-ui/icons/Menu'
import Drawer from 'material-ui/Drawer'
import Divider from 'material-ui/Divider'
import List, { ListItem, ListItemText } from 'material-ui/List'
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft'
import ChevronRightIcon from '@material-ui/icons/ChevronRight'

import AppRoutes from '../routes/AppRoutes'
import { getMenuItems } from '../util/applicationContext'
import { authenticateUser } from '../actions/authenticationJwtActions'

import styles from './AppStyles'

class App extends Component {
  constructor (props) {
    super(props)
    this.state = { open: false }
    this.handleDrawerOpen = this.handleDrawerOpen.bind(this)
    this.handleDrawerClose = this.handleDrawerClose.bind(this)
    this.renderMenu = this.renderMenu.bind(this)
  }

  handleDrawerOpen () {
    this.setState({ open: true })
  }

  handleDrawerClose () {
    this.setState({ open: false })
  }

  renderMenu () {
    const { classes, theme } = this.props
    const { open } = this.state
    return (
      <Drawer
        variant='persistent'
        anchor='left'
        open={open}
        classes={{ paper: classes.drawerPaper }}>
        <div className={classes.drawerHeader}>
          <IconButton onClick={this.handleDrawerClose}>
            {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
          </IconButton>
        </div>
        <Divider />
        <div className={classes.menuList}>
          <List component='nav'>
            {getMenuItems().map(it =>
              (<ListItem button key={it} onClick={this.handleDrawerClose}><ListItemText primary={it} /></ListItem>))}
          </List>
        </div>
      </Drawer>
    )
  }

  render () {
    const { classes, authentication } = this.props
    const { open } = this.state

    const toolBarContents = (
      <React.Fragment>
        <IconButton color='inherit' aria-label='open drawer' onClick={this.handleDrawerOpen}
          className={classNames(classes.menuButton, open && classes.hide)}>
          <MenuIcon />
        </IconButton>
        <Typography variant='title' color='inherit' className={classes.flex}>App</Typography>
        <Button color='inherit'>
          <Link className={classes.logoutButton} to='/logout'>Sair</Link>
        </Button>
      </React.Fragment>
    )
    return (
      <BrowserRouter>
        <div className={classes.root}>
          <div className={classes.appFrame}>
            <AppBar className={classNames(classes.appBar, {
              [classes.appBarShift]: open,
              [classes.appBarShiftLeft]: open})}>
              <Toolbar disableGutters={!open}>
                {authentication && authentication.isUserAuthenticated && toolBarContents}
              </Toolbar>
            </AppBar>
            {this.renderMenu()}
            <main
              className={classNames(classes.content, classes.contentLeft, {
                [classes.contentShift]: open,
                [classes.contentShiftLeft]: open})}>
              <div className={classes.drawerHeader} />
              <AppRoutes />
            </main>
          </div>
        </div>
      </BrowserRouter>
    )
  }
}

const mapStateToProps = ({ authentication }) => ({ authentication })

const materialUIEnhance = withStyles(styles, { withTheme: true })(App)

export default connect(mapStateToProps, { authenticateUser })(materialUIEnhance)
