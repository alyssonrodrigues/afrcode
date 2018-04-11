import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withStyles } from 'material-ui/styles'
import AppBar from 'material-ui/AppBar'
import Toolbar from 'material-ui/Toolbar'
import Typography from 'material-ui/Typography'
import Button from 'material-ui/Button'
import IconButton from 'material-ui/IconButton'
import MenuIcon from '@material-ui/icons/Menu'
import Menu, { MenuItem } from 'material-ui/Menu'

import { getMenuItems } from '../util/applicationContext'

const styles = {
  root: {
    flexGrow: 1
  },
  flex: {
    flex: 1
  },
  menuButton: {
    marginLeft: -12,
    marginRight: 20
  },
  logoutButton: {
    color: 'inherit',
    textDecoration: 'none'
  }
}

class AppMenu extends Component {
  constructor (props) {
    super(props)
    this.state = {
      anchorEl: null
    }
    this.handleClick = this.handleClick.bind(this)
    this.handleClose = this.handleClose.bind(this)
    this.renderMenu = this.renderMenu.bind(this)
  }

  handleClick (event) {
    this.setState({ anchorEl: event.currentTarget })
  }

  handleClose () {
    this.setState({ anchorEl: null })
  }

  renderMenu (anchorEl) {
    return (
      <Menu id='simple-menu'
        anchorEl={anchorEl}
        open={Boolean(anchorEl)}
        onClose={this.handleClose}>
        {getMenuItems().map(it => (<MenuItem key={it} onClick={this.handleClose}>{it}</MenuItem>))}
      </Menu>
    )
  }

  render () {
    const { classes } = this.props
    const { anchorEl } = this.state
    return (
      <div className={classes.root}>
        <AppBar position='static'>
          <Toolbar>
            <IconButton className={classes.menuButton} color='inherit' aria-label='Menu'
              aria-owns={anchorEl ? 'simple-menu' : null} aria-haspopup='true'
              onClick={this.handleClick}>
              <MenuIcon />
            </IconButton>
            {this.renderMenu(anchorEl)}
            <Typography variant='title' color='inherit' className={classes.flex}>Menu</Typography>
            <Button color='inherit'>
              <Link className={classes.logoutButton} to='/logout'>Sair</Link>
            </Button>
          </Toolbar>
        </AppBar>
      </div>
    )
  }
}

export default withStyles(styles)(AppMenu)
