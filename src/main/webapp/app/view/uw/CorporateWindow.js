Ext.define('compass.view.uw.CorporateWindow',{
    extend: 'compass.view.base.WindowForm',
    xtype: 'corporate-window',
    requires: [
        'compass.view.uw.CorpFormContainer'
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
                            glyph: compass.util.Glyphs.getGlyph('corporate')
                        },
                        {
                            xtype: 'corp-annivs-form',
                            glyph: compass.util.Glyphs.getGlyph('cover')
                        },
                        {
                            xtype: 'contact-form',
                            glyph: compass.util.Glyphs.getGlyph('contact')
                        }
                    ]
                }
            ]
        }
    ]

});
