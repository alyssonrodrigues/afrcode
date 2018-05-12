import React, { Component } from 'react'
import { withStyles } from 'material-ui/styles'
import { connect } from 'react-redux'
import classNames from 'classnames'
import AppBar from 'material-ui/AppBar'
import Toolbar from 'material-ui/Toolbar'
import Typography from 'material-ui/Typography'
import IconButton from 'material-ui/IconButton'
import MenuIcon from '@material-ui/icons/Menu'

import styles from './AppToolbarStyles'
import UserMenu from './UserMenu'

class AppToolbar extends Component {
  renderToobalTitle () {
    const { classes } = this.props
    return (
      <Typography variant='title' color='inherit' className={classes.flex}>App</Typography>
    )
  }

  renderMainMenuIconButton () {
    const { classes, mainMenuOpen, handleMainMenuOpen } = this.props
    return (
      <IconButton color='inherit' aria-label='open drawer' onClick={handleMainMenuOpen}
        className={classNames(classes.menuButton, mainMenuOpen && classes.hide)}>
        <MenuIcon />
      </IconButton>
    )
  }

  renderToolbarContents () {
    const {
      authentication,
      userMenuAnchorEl,
      handleUserMenuOpen,
      handleUserMenuClose
    } = this.props
    return authentication && authentication.isUserAuthenticated && (
      <React.Fragment>
        {this.renderMainMenuIconButton()}
        {this.renderToobalTitle()}
        <UserMenu
          userMenuAnchorEl={userMenuAnchorEl}
          handleUserMenuOpen={handleUserMenuOpen}
          handleUserMenuClose={handleUserMenuClose} />
      </React.Fragment>
    )
  }

  render () {
    const { classes, mainMenuOpen } = this.props
    return (
      <AppBar className={classNames(classes.appBar, {
        [classes.appBarShift]: mainMenuOpen,
        [classes.appBarShiftLeft]: mainMenuOpen})}>
        <Toolbar>
          {this.renderToolbarContents()}
        </Toolbar>
      </AppBar>
    )
  }
}

const materialUIEnhanced = withStyles(styles, { withTheme: true })(AppToolbar)

const mapStateToProps = ({ authentication }) => ({ authentication })

export default connect(mapStateToProps)(materialUIEnhanced)
