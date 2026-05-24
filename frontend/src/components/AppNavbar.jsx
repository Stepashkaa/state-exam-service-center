import { Link, NavLink, useNavigate } from 'react-router-dom'
import { useAuth } from '../context/authContext'

const AppNavbar = () => {
  const navigate = useNavigate()
  const { isAuthenticated, username, role, logout } = useAuth()

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container">
        <Link className="navbar-brand fw-bold" to="/dashboard">
          Service Center
        </Link>

        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarContent"
        >
          <span className="navbar-toggler-icon" />
        </button>

        <div className="collapse navbar-collapse" id="navbarContent">
          {isAuthenticated && (
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <NavLink className="nav-link" to="/dashboard">
                  Главная
                </NavLink>
              </li>

              <li className="nav-item">
                <NavLink className="nav-link" to="/clients">
                  Клиенты
                </NavLink>
              </li>

              <li className="nav-item">
                <NavLink className="nav-link" to="/reports">
                  Отчеты
                </NavLink>
              </li>
            </ul>
          )}

          <div className="d-flex align-items-center gap-3 ms-auto">
            {isAuthenticated ? (
              <>
                <span className="text-light small">
                  {username} / {role}
                </span>

                <button className="btn btn-outline-light btn-sm" onClick={handleLogout}>
                  Выйти
                </button>
              </>
            ) : (
              <>
                <Link className="btn btn-outline-light btn-sm" to="/login">
                  Войти
                </Link>

                <Link className="btn btn-warning btn-sm" to="/register">
                  Регистрация
                </Link>
              </>
            )}
          </div>
        </div>
      </div>
    </nav>
  )
}

export default AppNavbar