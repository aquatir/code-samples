defmodule EnchanterShopTest do
  use ExUnit.Case
  doctest EnchanterShop

  test "enchant_for_sale_all should enchant all" do

    result = EnchanterShop.enchant_for_sale_all(EnchanterShop.test_data)
    expected_result =  [
      %{title: "Edwin's Longsword", price: 150, magic: true},
      %{title: "Edwin's Healing Potion", price: 180, magic: true},
      %{title: "Edwin's Rope", price: 30, magic: true},
      %{title: "Edwin's Dragon's Spear", price: 300, magic: true},
    ]
    assert (result == expected_result)
  end

  test "enchant_for_sale_non_magic should enchant only non-magic items" do

    result = EnchanterShop.enchant_for_sale_non_magic(EnchanterShop.test_data)
    expected_result =  [
      %{title: "Edwin's Longsword", price: 150, magic: true},
      %{title: "Healing Potion", price: 60, magic: true},
      %{title: "Edwin's Rope", price: 30, magic: true},
      %{title: "Dragon's Spear", price: 100, magic: true},
    ]
    assert (result == expected_result)

  end
end
