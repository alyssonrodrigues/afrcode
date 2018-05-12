import React, { Component } from 'react'
import { withStyles } from 'material-ui/styles'
import { connect } from 'react-redux'
import IconButton from 'material-ui/IconButton'
import Drawer from 'material-ui/Drawer'
import Divider from 'material-ui/Divider'
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft'
import ChevronRightIcon from '@material-ui/icons/ChevronRight'
import List, { ListItem, ListItemText } from 'material-ui/List'

import styles from './AppMenuStyles'
import { getMenuItems } from '../util/applicationContext'

class AppMenu extends Component {
  renderMainMenuItems () {
    const { classes, authentication, handleMainMenuClose } = this.props
    return authentication && authentication.isUserAuthenticated && (
      <div className={classes.menuList}>
        <List component='nav'>
          {getMenuItems().map(it => (
            <ListItem button key={it.label} onClick={() => handleMainMenuClose(it.path)}>
              <ListItemText primary={it.label} />
            </ListItem>
          ))}
          <Divider />
          <ListItem button key='Logout' onClick={() => handleMainMenuClose('/logout')}>
            <ListItemText primary='Sair' />
          </ListItem>
        </List>
      </div>
    )
  }

  render () {
    const {
      classes,
      theme,
      mainMenuOpen,
      handleMainMenuClose
    } = this.props
    return (
      <Drawer
        variant='persistent'
        anchor='left'
        open={mainMenuOpen}
        classes={{ paper: classes.drawerPaper }}>
        <div className={classes.drawerHeader}>
          <IconButton onClick={handleMainMenuClose}>
            {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
          </IconButton>
        </div>
        <Divider />
        {this.renderMainMenuItems()}
      </Drawer>
    )
  }
}

const materialUIEnhanced = withStyles(styles, { withTheme: true })(AppMenu)

const mapStateToProps = ({ authentication }) => ({ authentication })

export default connect(mapStateToProps)(materialUIEnhanced)
