import React from 'react';

const CompanyContext = React.createContext({
    company: {},
    setCompany: () => { },

    companies: [],
    setCompanies: () => { }
})

export default CompanyContext;