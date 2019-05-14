import React, { Component } from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import classNames from 'classnames'
import { withStyles } from '@material-ui/core/styles'
import Portal from '@material-ui/core/Portal'

import styles from './AppStyles'
import AppToolbar from './AppToolbar'
import MessagesBar from './MessagesBar'
import ProgressDialog from './ProgressDialog'
import { openMenu, closeMenu } from '../actions/mainMenuActions'

class App extends Component {
  constructor (props) {
    super(props)
    this.state = {
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
    this.props.openMenu()
    this.setState({ mainMenuOpen: true })
  }

  handleMainMenuClose (path) {
    this.props.closeMenu()
    this.setState({ mainMenuOpen: false })
    this.redirect(path)
  }

  render () {
    const { classes, children, noAppToolbar, mainMenu: { mainMenuOpen } } = this.props
    const { userMenuAnchorEl } = this.state
    return (
      <div className={classes.appFrame}>
        {!noAppToolbar && (<AppToolbar
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
          <Portal>
            <MessagesBar />
          </Portal>
          <ProgressDialog />
        </main>
      </div>
    )
  }
}

const materialUIEnhanced = withStyles(styles)(App)

const mapStateToProps = ({ mainMenu }) => ({ mainMenu })

const reduxEnhanced = connect(mapStateToProps, { openMenu, closeMenu })(materialUIEnhanced)

export default withRouter(reduxEnhanced)
