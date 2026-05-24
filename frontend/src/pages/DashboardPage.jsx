import { Link } from 'react-router-dom'
import { useAuth } from '../context/authContext'

const DashboardPage = () => {
  const { username, role } = useAuth()

  return (
    <div className="container py-4">
      <div className="p-4 p-md-5 mb-4 bg-light rounded-3 border">
        <div className="container-fluid py-3">
          <h1 className="display-6 fw-bold">
            Система учета клиентов сервисного центра
          </h1>

          <p className="col-md-8 fs-5">
            Приложение для подготовки к государственному экзамену.
            Реализует учет клиентов, выдачу услуги или предмета,
            назначение сотрудника, фильтрацию, поиск и отчеты.
          </p>

          <div className="alert alert-info">
            Вы вошли как <b>{username}</b>. Роль: <b>{role}</b>.
          </div>

          <div className="d-flex gap-2 flex-wrap">
            <Link to="/clients" className="btn btn-primary">
              Перейти к клиентам
            </Link>

            <Link to="/reports" className="btn btn-outline-primary">
              Открыть отчеты
            </Link>
          </div>
        </div>
      </div>

      <div className="row g-3">
        <div className="col-12 col-md-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body">
              <h5 className="card-title">Клиенты</h5>
              <p className="card-text">
                Добавление, редактирование, поиск и фильтрация клиентов.
              </p>
            </div>
          </div>
        </div>

        <div className="col-12 col-md-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body">
              <h5 className="card-title">Операции</h5>
              <p className="card-text">
                Назначение сотрудника, выдача предмета, продление срока,
                перевод на уровень.
              </p>
            </div>
          </div>
        </div>

        <div className="col-12 col-md-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body">
              <h5 className="card-title">Отчеты</h5>
              <p className="card-text">
                Подсчет активных, завершенных клиентов и клиентов с выданными предметами.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default DashboardPage