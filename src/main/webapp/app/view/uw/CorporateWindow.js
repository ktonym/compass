Ext.define('compass.view.uw.CorporateWindow',{
    extend: 'compass.view.base.WindowForm',
    xtype: 'corporate-window',
    requires: [
        'compass.view.uw.CorpFormContainer','compass.util.Glyphs'
    ],

    width: 537,

    items: [
        {
            xtype: 'form',
            reference: 'corpForm',
            layout: {
                type: 'fit'
            },
            items: [
                {
                    xtype: 'tabpanel',
                    activeTab: 0,
                    items: [
                        {
                            xtype: 'corp-form-container',
                            glyph: compass.util.Glyphs.getGlyph('corporate'),
                            title: 'Corporate Info'
                        },
                        {
                            xtype: 'corp-annivs-form',
                            glyph: compass.util.Glyphs.getGlyph('cover'),
                            title: 'Anniversaries',
                            reference: 'corpAnnivsForm',
                            dockedItems: [{
                                dock: 'top',
                                items: [
                                    //{
                                    //    xtype: 'combobox',
                                    //    fieldLabel: 'Existing cover',
                                    //    queryMode: 'local',
                                    //    store: '{currentCorporate.annivs}',
                                    //    //bind: {
                                    //    //    store: currentCorporate
                                    //    //},
                                    //    displayField: 'anniv',
                                    //    valueField: 'idCorpAnniv',
                                    //    listeners: {
                                    //        'select': 'onSelectCover'
                                    //    }
                                    //},
                                    {
                                        xtype: 'button',
                                        text: 'Create new',
                                        glyph: compass.util.Glyphs.getGlyph('add'),
                                        listeners: {
                                            onClick: 'onAddCover'
                                        }
                                    }
                                ]
                            }],
                            bind: {
                                hidden: '{!isEdit}'
                            }
                        },
                        {
                            xtype: 'contact-form',
                            glyph: compass.util.Glyphs.getGlyph('contact'),
                            title: 'Contacts',
                            bind: {
                                hidden: '{isEdit}'
                            }
                        }
                    ]
                }
            ]
        }
    ]

});
