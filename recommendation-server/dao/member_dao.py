import mysql.connector.pooling

# 커넥션 풀 생성
connection_pool = mysql.connector.pooling.MySQLConnectionPool(
    host="127.0.0.1",
    port=3306,
    user="ssafy",
    password="ssafy",
    database="newkids",
    charset="utf8"
)


def get_member_id_by_member_key(member_key: str):
    row = None
    connection = None

    try:
        connection = connection_pool.get_connection()
        cursor = connection.cursor()

        sql = (
            "SELECT `member_id` "
            "FROM `members` "
            "WHERE `member_key` = %s "
        )

        cursor.execute(sql, (member_key,))
        row = cursor.fetchone()
        cursor.close()

    except Exception as e:
        print(f"Exception={e}")
    finally:
        if connection:
            connection.close()

    return row


def get_members_all():
    row = None
    connection = None

    try:
        connection = connection_pool.get_connection()
        cursor = connection.cursor()

        sql = (
            "SELECT *  "
            "FROM `members` "
        )

        cursor.execute(sql)
        row = cursor.fetchall()
        cursor.close()

    except Exception as e:
        print(f"Exception={e}")
    finally:
        if connection:
            connection.close()

    return row
