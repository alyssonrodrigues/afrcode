import React, { Component } from 'react'
import { withStyles } from '@material-ui/core/styles'
import { connect } from 'react-redux'
import IconButton from '@material-ui/core/IconButton'
import Drawer from '@material-ui/core/Drawer'
import Divider from '@material-ui/core/Divider'
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft'
import ChevronRightIcon from '@material-ui/icons/ChevronRight'
import List from '@material-ui/core/List'
import ListItem from '@material-ui/core/ListItem'
import ListItemText from '@material-ui/core/ListItemText'

import styles from './AppMenuStyles'
import { getMenuItems } from '../util/applicationContext'

export class AppMenu extends Component {
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
      mainMenu: { mainMenuOpen },
      handleMainMenuClose
    } = this.props
    return (
      <Drawer
        variant='persistent'
        anchor='left'
        open={mainMenuOpen}
        PaperProps={{style: {minHeight: '100%'}}}
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

const mapStateToProps = ({ authentication, mainMenu }) => ({ authentication, mainMenu })

export default connect(mapStateToProps)(materialUIEnhanced)
