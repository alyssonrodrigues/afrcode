const authTokenKey = 'authTokenKey';

export const saveSecurityToken = (authentication) => {
  try {
    localStorage.setItem(authTokenKey, JSON.stringify(authentication));
    return authentication;
  } catch (err) {
    // TODO log err
    throw err;
  }
};

export const removeSecurityToken = () => {
  try {
    localStorage.removeItem(authTokenKey);
    return authTokenKey;
  } catch (err) {
    // TODO log err
    throw err;
  }
};

export const getAuthentication = () => {
  try {
    const item = localStorage.getItem(authTokenKey);
    return item ? JSON.parse(item) : item;
  } catch (err) {
    // TODO log err
    throw err;
  }
};