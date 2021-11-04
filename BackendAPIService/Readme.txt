List api for backend server:
1. Database:
Column: User, Product, Brand, Cart, CartDetail, Order, OrderDetail
Actor: Guest, User, Admin
2. API:
    2.1: Public API
        2.1.1: Access: Sign up, Sign in                                                                                 OK
        2.1.2: Guest: All product, Search Product                                                                       OK
    2.2: API have Role
        2.2.1 User API
            2.2.1.1 Cart api
                - add product to cart:                                                                                  OK
                    param: product_id
                    des: add product quantity in cart( +1 quantity) in cart detail
                - reduce product in cart:                                                                               OK
                    param: product_id
                    des: reduce product quantity in cart (-1 quantity). If dont exist product: response error
                - overview cart:                                                                                        OK
                    des: response all product in cart for user
            2.2.1.2 Order api:
                - create order:                                                                                         OK
                    des: move cart of user -> order of user consist: address, delevery_type, ...
                - order history:                                                                                        OK
                    des: view all order of user
            2.2.1.3 My User api:
                - my info:
                    des: response all info of user
                - change info:
                    requestBody: info of user to change
                    des: change info of user
                - change password:
                    requestBody: oldPassword, newPassword
                    des: identity and change pass to newPassword
        2.2.2 Admin API
            2.2.2.1 User Manager api
                - block user:
                    params: user_id or username
                    des: block user
                - view all user:
                    des: view all info of user
            2.2.2.2 Order Manager api
                - view all order:
                    des: get list order of shop
                - view all order unpaid:
                    des: get list order of shop unpaid
                - confirm order paid:
                    params: order_id
                    des: confirm order paid : state_order = 1
                - reject order:
                    params: order_id
                    des: reject order: state_order = -1
            2.2.2.3: Statistical api:
                - view total money:
                    param: from_date, to_date
                    des: get total money...
                - view total order from_date, to_date:
