import { useState } from 'react'
import { authService } from '../services/authService'
import { AuthContext } from './authContext'

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(localStorage.getItem('token'))
  const [username, setUsername] = useState(localStorage.getItem('username'))
  const [role, setRole] = useState(localStorage.getItem('role'))

  const isAuthenticated = Boolean(token)
  const isAdmin = role === 'ADMIN'

  const login = async (formData) => {
    const response = await authService.login(formData)

    localStorage.setItem('token', response.token)
    localStorage.setItem('username', response.username)
    localStorage.setItem('role', response.role)

    setToken(response.token)
    setUsername(response.username)
    setRole(response.role)

    return response
  }

  const register = async (formData) => {
    const response = await authService.register(formData)

    localStorage.setItem('token', response.token)
    localStorage.setItem('username', response.username)
    localStorage.setItem('role', response.role)

    setToken(response.token)
    setUsername(response.username)
    setRole(response.role)

    return response
  }

  const logout = () => {
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    localStorage.removeItem('role')

    setToken(null)
    setUsername(null)
    setRole(null)
  }

  return (
    <AuthContext.Provider
      value={{
        token,
        username,
        role,
        isAuthenticated,
        isAdmin,
        login,
        register,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  )
}