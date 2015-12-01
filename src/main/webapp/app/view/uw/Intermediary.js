Ext.define('compass.view.uw.Intermediary',{

    extend: 'Ext.panel.Panel',
    xtype: 'intermediary',
    requires: [
        'compass.model.uw.Intermediary',
        'compass.model.TextCombo',
        'compass.util.Glyphs',
        'compass.view.base.TopToolbar',
        'compass.view.uw.IntermediaryGrid',
        'compass.view.uw.IntermediaryController',
        'compass.view.uw.IntermediaryModel'
    ],
    controller: 'intermediary',
    viewModel: {
        type: 'intermediary'
    },
    frame: true,
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items:[
        {
            xtype: 'intermediary-grid',
            flex: 1
        }
    ],
    dockedItems: [{
        xtype: 'top-tool-bar'
    }]

});
