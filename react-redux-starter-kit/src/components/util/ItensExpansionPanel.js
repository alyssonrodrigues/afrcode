import React, { Component } from 'react'
import { withStyles } from '@material-ui/core/styles'
import ExpansionPanel from '@material-ui/core/ExpansionPanel'
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary'
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails'
import Add from '@material-ui/icons/Add'
import Remove from '@material-ui/icons/Remove'
import Badge from '@material-ui/core/Badge'
import TablePagination from '@material-ui/core/TablePagination'
import Grid from '@material-ui/core/Grid/Grid'
import classNames from 'classnames'
import _ from 'lodash'

import styles from './ItensExpansionPanelStyles'

class ItensExpansionPanel extends Component {
  constructor (props) {
    super(props)
    this.state = {
      expanded: false,
      page: 0
    }
  }

  getItensComponentsSliced () {
    const { numItensVisiveis, expandedRowsPerPage, itensComponents: ItensComponents } = this.props
    const { page } = this.state
    const numItens = _.size(ItensComponents)
    const start = expandedRowsPerPage ? page * (numItensVisiveis + expandedRowsPerPage) : 0
    const end = expandedRowsPerPage ? start + (numItensVisiveis + expandedRowsPerPage) : numItens
    return _.slice(ItensComponents, start, end)
  }

  renderExpandIcon () {
    const {
      classes,
      numItensVisiveis,
      itensComponents: ItensComponents,
      expandIconClassName,
      noBadge
    } = this.props
    const { expanded } = this.state
    const temItensAExpandir = _.size(ItensComponents) > numItensVisiveis
    return temItensAExpandir
      ? (expanded
        ? (<Remove />)
        : noBadge ? (
          <Add className={expandIconClassName} />
        ) : (
          <Badge badgeContent={_.size(ItensComponents) - numItensVisiveis} classes={{badge: classes.ajusteBadge}}>
            <Add className={expandIconClassName} />
          </Badge>
        )
      )
      : null
  }

  renderItensVisiveis (ItensComponentsVisiveis) {
    return ItensComponentsVisiveis
  }

  renderItensAExpandir (ItensComponentsAExpandir) {
    const { numItensVisiveis, expandedRowsPerPage, itensComponents: ItensComponents } = this.props
    const { page } = this.state
    const numItens = _.size(ItensComponents)
    return (
      <React.Fragment>
        {ItensComponentsAExpandir}
        {expandedRowsPerPage && (numItens > (numItensVisiveis + expandedRowsPerPage)) ? (
          <TablePagination
            component={Grid}
            item
            xs={12}
            count={numItens} page={page} rowsPerPage={numItensVisiveis + expandedRowsPerPage}
            labelRowsPerPage={null}
            labelDisplayedRows={({from, to, count}) => `${from}-${to} de ${count}`}
            rowsPerPageOptions={[]}
            onChangePage={(event, page) => this.setState({page})}
            onChangeRowsPerPage={null} />
        ) : null}
      </React.Fragment>
    )
  }

  render () {
    const {
      classes,
      numItensVisiveis,
      expandedRowsPerPage,
      itensComponents: ItensComponents,
      expandIcon,
      noExpandIcon,
      expanded,
      onChange,
      defaultExpanded,
      IconButtonProps
    } = this.props
    if (!numItensVisiveis || !_.size(ItensComponents)) {
      return null
    }
    const numItens = _.size(ItensComponents)
    const temItensAExpandir = numItens > numItensVisiveis
    const ItensComponentsSliced = this.getItensComponentsSliced()
    const ItensComponentsVisiveis = _.slice(ItensComponentsSliced, 0, numItensVisiveis)
    const size = expandedRowsPerPage ? numItensVisiveis + expandedRowsPerPage : numItens
    const ItensComponentsAExpandir = _.slice(ItensComponentsSliced, numItensVisiveis, size)
    return (
      <ExpansionPanel elevation={0} className={classes.paper}
        classes={{root: classes.expansionPanelRoot, expanded: classes.expansionPanelExpanded}}
        expanded={expanded} onChange={onChange} defaultExpanded={defaultExpanded}>
        <ExpansionPanelSummary
          expandIcon={noExpandIcon ? null : expandIcon || this.renderExpandIcon()}
          IconButtonProps={IconButtonProps}
          classes={{
            root: classNames(classes.expansionPanelSummaryRoot,
              { [classes.expansionPanelSummaryRootWithNoContent]: !temItensAExpandir }),
            content: classes.expansionPanelSummaryContent,
            expanded: classes.expansionPanelSummaryExpanded,
            expandIcon: expandIcon ? null : classes.expansionPanelExpandIcon
          }}
          onClick={() => this.setState(prevState => ({ expanded: !prevState.expanded }))}>
          {this.renderItensVisiveis(ItensComponentsVisiveis)}
        </ExpansionPanelSummary>
        <ExpansionPanelDetails classes={{root: classes.expansionPanelDetailsRoot}}>
          {this.renderItensAExpandir(ItensComponentsAExpandir)}
        </ExpansionPanelDetails>
      </ExpansionPanel>
    )
  }
}

export default withStyles(styles)(ItensExpansionPanel)
