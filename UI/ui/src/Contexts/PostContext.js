import React from 'react';

const PostContext = React.createContext({
    post: {},
    setPost: () => { },

    posts: [],
    setPosts: () => { }
})

export default PostContext;