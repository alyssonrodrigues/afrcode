import React, { Component } from 'react'
import { withRouter } from 'react-router-dom'
import classNames from 'classnames'
import { withStyles } from '@material-ui/core/styles'

import styles from './AppStyles'
import AppToolbar from './AppToolbar'
import MessagesBar from './MessagesBar'
import ProgressDialog from './ProgressDialog'

class App extends Component {
  constructor (props) {
    super(props)
    this.state = {
      mainMenuOpen: false,
      userMenuAnchorEl: null
    }
    this.redirect = this.redirect.bind(this)
    this.handleMainMenuOpen = this.handleMainMenuOpen.bind(this)
    this.handleMainMenuClose = this.handleMainMenuClose.bind(this)
    this.handleUserMenuOpen = this.handleUserMenuOpen.bind(this)
    this.handleUserMenuClose = this.handleUserMenuClose.bind(this)
  }

  redirect (path) {
    if (path) this.props.history.push(path)
  }

  handleUserMenuOpen (event) {
    this.setState({ userMenuAnchorEl: event.currentTarget })
  }

  handleUserMenuClose (path) {
    this.setState({ userMenuAnchorEl: null })
    this.redirect(path)
  }

  handleMainMenuOpen () {
    this.setState({ mainMenuOpen: true })
  }

  handleMainMenuClose (path) {
    this.setState({ mainMenuOpen: false })
    this.redirect(path)
  }

  render () {
    const { classes, children, noAppToolbar } = this.props
    const { mainMenuOpen, userMenuAnchorEl } = this.state
    return (
      <div className={classes.appFrame}>
        {!noAppToolbar && (<AppToolbar
          mainMenuOpen={mainMenuOpen}
          handleMainMenuOpen={this.handleMainMenuOpen}
          handleMainMenuClose={this.handleMainMenuClose}
          userMenuAnchorEl={userMenuAnchorEl}
          handleUserMenuOpen={this.handleUserMenuOpen}
          handleUserMenuClose={this.handleUserMenuClose} />)}
        <main
          className={classNames(classes.content, {
            [classes.contentLeft]: !noAppToolbar,
            [classes.contentShift]: !noAppToolbar && mainMenuOpen,
            [classes.contentShiftLeft]: !noAppToolbar && mainMenuOpen})}>
          {!noAppToolbar && (<div className={classes.drawerHeader} />)}
          {children}
          <MessagesBar />
          <ProgressDialog />
        </main>
      </div>
    )
  }
}

const materialUIEnhance = withStyles(styles)(App)

export default withRouter(materialUIEnhance)
