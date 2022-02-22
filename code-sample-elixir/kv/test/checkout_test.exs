defmodule CheckoutTest do
  use ExUnit.Case
  doctest Checkout

  test "total is correct on integer" do
    assert (Checkout.total_cost(10, 1.0) == 20)
  end

  test "total is correct on double" do
    assert (Checkout.total_cost(10, 0.5) == 15)
  end
end
