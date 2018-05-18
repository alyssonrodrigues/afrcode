import React, { Component } from 'react'
import { withStyles } from 'material-ui/styles'
import { connect } from 'react-redux'
import _ from 'lodash'
import IconButton from 'material-ui/IconButton'
import Avatar from 'material-ui/Avatar'
import Button from 'material-ui/Button'
import Menu from 'material-ui/Menu'
import Card, { CardActions, CardContent } from 'material-ui/Card'
import Typography from 'material-ui/Typography'

import styles from './UserMenuStyles'

class UserMenu extends Component {
  handleUserLogout () {
    const { handleUserMenuClose, handleMainMenuClose } = this.props
    handleMainMenuClose()
    handleUserMenuClose('/logout')
  }

  renderUserMenuIconButton (userMenuId) {
    const {
      classes,
      authentication: { username },
      userMenuAnchorEl,
      handleUserMenuOpen
    } = this.props
    return (
      <IconButton aria-owns={userMenuAnchorEl ? userMenuId : null} aria-haspopup='true' color='inherit'
        onClick={handleUserMenuOpen}>
        <Avatar className={classes.avatar}>{_.toUpper(username[0])}</Avatar>
      </IconButton>
    )
  }

  renderUserMenuItems () {
    return (
      <Button fullWidth color='primary' variant='raised' onClick={this.handleUserLogout.bind(this)}>Sair</Button>
    )
  }

  renderUserMenu (userMenuId) {
    const {
      classes,
      authentication: { username },
      userMenuAnchorEl,
      handleUserMenuClose
    } = this.props
    return (
      <Menu
        id={userMenuId}
        anchorEl={userMenuAnchorEl}
        open={Boolean(userMenuAnchorEl)}
        onClose={handleUserMenuClose}
        className={classes.userMenu}>
        <Card elevation={0}>
          <CardContent>
            <Typography variant='body2' color='textSecondary'>{username}</Typography>
          </CardContent>
          <CardActions className={classes.row}>
            {this.renderUserMenuItems()}
          </CardActions>
        </Card>
      </Menu>
    )
  }

  render () {
    const userMenuId = 'user-menu'
    const { classes, authentication } = this.props
    return authentication && authentication.isUserAuthenticated && (
      <div className={classes.row}>
        {this.renderUserMenuIconButton(userMenuId)}
        {this.renderUserMenu(userMenuId)}
      </div>
    )
  }
}

const materialUIEnhanced = withStyles(styles)(UserMenu)

const mapStateToProps = ({ authentication }) => ({ authentication })

export default connect(mapStateToProps)(materialUIEnhanced)
