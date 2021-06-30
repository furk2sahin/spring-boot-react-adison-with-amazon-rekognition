import React from 'react';

const AdContext = React.createContext({
    ad: {},
    setAd: () => { },

    ads: [],
    setAds: () => { }
})

export default AdContext;