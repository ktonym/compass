Ext.define('compass.view.uw.BenefitRef',{
   extend: 'Ext.panel.Panel',
    xtype: 'benefitref',
    requires: [
        'compass.model.uw.BenefitRef',
        'compass.util.Glyphs',
        'compass.view.base.TopToolbar',
        'compass.view.uw.BenefitRefGrid',
        'compass.view.uw.BenefitRefModel',
        'compass.view.uw.BenefitRefController'
    ],
    controller: 'benefitref',
    viewModel: {
        type: 'benefitref'
    },

    session: true,

    frame: true,

    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    items: [
        {
            xtype: 'benefitref-grid',
            flex: 1
        }
    ],

    dockedItems: [{
        xtype: 'top-tool-bar'
    }]


    //dockedItems: [
    //    {
    //        xtype: "toolbar",
    //        dock: "top",
    //        items: [
    //            {
    //                xtype: 'button',
    //                text: 'Add',
    //                glyph: compass.util.Glyphs.getGlyph('add'),
    //                listeners: {
    //                    click: 'onAdd'
    //                }
    //
    //            },
    //            {
    //                xtype: 'button',
    //                text: 'Edit',
    //                glyph: compass.util.Glyphs.getGlyph('edit'),
    //                listeners: {
    //                    click: 'onEdit'
    //                },
    //                bind: {
    //                    disabled: '{!benefitRefGrid.selection}'
    //                }
    //
    //            },
    //            {
    //                xtype: 'button',
    //                text: 'Delete',
    //                glyph: compass.util.Glyphs.getGlyph('destroy'),
    //                listeners: {
    //                    click: 'onDelete'
    //                },
    //                bind: {
    //                    disabled: '{!benefitRefGrid.selection}'
    //                }
    //
    //            }
    //        ]
    //    }
    //]

});
