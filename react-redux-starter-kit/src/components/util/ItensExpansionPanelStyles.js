export default theme => ({
  paper: {
    backgroundColor: 'unset',
    paddingTop: 0,
    paddingBottom: 0,
    paddingLeft: 0,
    paddingRight: 0,
    width: '100%',
    maxWidth: '100%'
  },
  expansionPanelRoot: {
    margin: '0 0 0 0 !important'
  },
  expansionPanelExpanded: {
    margin: '0 0 0 0 !important'
  },
  expansionPanelSummaryRoot: {
    padding: '0 0 0 0 !important',
    minHeight: 'auto !important'
  },
  expansionPanelSummaryRootWithNoContent: {
    cursor: 'default !important'
  },
  expansionPanelSummaryContent: {
    marginTop: 0,
    marginBottom: 0,
    maxWidth: '100%'
  },
  expansionPanelExpandIcon: {
    transform: 'translateY(-50%) rotate(0deg) !important'
  },
  expansionPanelSummaryExpanded: {
    margin: '0 0 0 0 !important'
  },
  expansionPanelDetailsRoot: {
    padding: '0 0 0 0 !important',
    margin: '0 0 0 0 !important',
    flexWrap: 'wrap'
  },
  ajusteBadge: {
    top: '50%',
    right: '-25%',
    fontSize: theme.typography.body2Next.fontSize
  },
  badgePosition: {
    transform: 'translate(-50%)'
  }
})
