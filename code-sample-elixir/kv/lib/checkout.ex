defmodule Checkout do
  defguard is_rate(value) when is_float(value) and value >= 0 and value <= 1
  defguard is_cents(value) when is_integer(value) and value >= 0

  def total_cost(price, tax_rate) when is_cents(price) and is_rate(tax_rate) do
      price + tax_cost(price, tax_rate)
  end

  def tax_cost(price, tax_rate) when is_cents(price) and is_rate(tax_rate) do
      price * tax_rate
  end
end
